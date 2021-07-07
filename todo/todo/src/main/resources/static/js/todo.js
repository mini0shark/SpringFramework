const toDoForm = document.querySelector(".js-toDoForm"),
toDoSubject = document.querySelector("#subject"),
toDoContent = toDoForm.querySelector("#content"),
toDoList = document.querySelector(".js-toDoList");

const todo = document.getElementById("todo1");


const TODOS_LS = 'toDos';   //localStorage
let todoList = []

function saveToDos(){
    localStorage.setItem(TODOS_LS, JSON.stringify(todoList));
}


function deleteTodo(event){
    const btn = event.target;
    const li = btn.parentNode;
    toDoList.removeChild(li);
    const cleanToDos = todoList.filter(function(toDo){return parseInt(li.id)!==parseInt(toDo.id)});
    console.log(cleanToDos, li.id);
    todoList = cleanToDos;
    saveToDos();
}
function finTodo(event){
    const shape = event.target.parentNode;
    if(shape.className==="done"){
        shape.className="";
    }else{
        shape.className="done";
    }
}
function afterResponse(element){
    const li = document.createElement("li");
    const delBtn = document.createElement("button");
    const shape = document.createElement("span");
    const subject = document.createElement("span");
    const content = document.createElement("span");
    const newId = todoList.length+1;
    delBtn.innerText = "X";
    delBtn.addEventListener("click", deleteTodo);
    subject.innerHTML = element.subject;
    content.innerHTML = element.content;
    shape.appendChild(subject);
    shape.appendChild(content);
    shape.addEventListener("click", finTodo);
    li.appendChild(shape);
    li.appendChild(delBtn);
    toDoList.appendChild(li);

    const toDoObj={
        id:element.id,
        subject:element.subject,
        content:element.content,
        createDate:element.createDate,
        fin:element.fin
    };
    todoList.push(toDoObj);
}
function paintTodo(sbj, cnt){
    fetch("http://localhost:8080/todo",{
        method :'POST',
        headers:{
            'Content-Type': 'application/json',
            'charset':'utf-8'
        }
    }).then(function(response){
        return response.json();
    }).then(function(json){
        console.log(json);
        afterResponse(json);
    });
}
function handleSubmit(event){
    event.preventDefault();
    const currentSubject = toDoSubject.value;
    const currentContent = toDoContent.value;
    paintTodo(currentSubject, currentContent);
}
function loadToDos(){
    fetch("http://localhost:8080/todo-list").then(function(response){
        return response.json();
    }).then(function(json){
        console.log(json);
        json.forEach(element=>{
            afterResponse(element);
        })
    });
}
function ex(){
    const removeList = []
    const childList = toDoList.childNodes
    childList.forEach(function(element){
        removeList.push(element);
    });
    removeList.forEach(function(e){
        toDoList.removeChild(e);
    })
}
function init(){
    loadToDos();
    //ex();
    toDoForm.addEventListener("submit", handleSubmit);
}
init();