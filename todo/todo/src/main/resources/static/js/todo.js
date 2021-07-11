const toDoForm = document.querySelector(".js-toDoForm"),
toDoSubject = document.querySelector("#subject"),
toDoContent = toDoForm.querySelector("#content"),
toDoList = document.querySelector(".js-toDoList");

const todo = document.getElementById("todo1");


const TODOS_LS = 'toDos';   //localStorage
let todos = new Map();



function deleteTodo(event){
    const btn = event.target;
    fetch("http://localhost:8080/todo/"+btn.name,{
        method :'DELETE'
    }).then(function(response){
        return response.json();
    }).then(function(json){
        if(json){
            const li = btn.parentNode;
            todos.delete()
            toDoList.removeChild(li);
            const cleanToDos = todoList.filter(function(toDo){return parseInt(li.id)!==parseInt(toDo.id)});
            console.log(cleanToDos, li.id);
        }else{
            alert("서버의 문제로 실패했습니다.");
        }
    });
    
}
function finTodo(event){
    const shape = event.target.parentNode;
    const todoId = Number(shape.id);
    const element = todos.get(todoId);
    console.log(todos);
    console.log(todoId);
    if(element.fin){
        element.fin=false;
    }else{
        element.fin=true;
    }
    
    fetch("http://localhost:8080/todo",{
        method :'PUT',
        headers:{
            'Content-Type': 'application/json',
            'charset':'utf-8'
        }, body:JSON.stringify(element)
    }).then(function(response){
        return response.json();
    }).then(function(json){
        if(json.fin){
            shape.className="contents done";
        }else{
            shape.className="contents";
        }
        console.log(json);
        todos.set(todoId, json);
    });
}
function afterResponse(element){
    const li = document.createElement("li");
    const delBtn = document.createElement("button");
    const shape = document.createElement("span");
    const subject = document.createElement("span");
    const content = document.createElement("span");
    delBtn.innerText = "X";
    delBtn.name = element.id;
    delBtn.addEventListener("click", deleteTodo);
    subject.innerHTML = element.subject;
    content.innerHTML = element.content;
    shape.id=element.id;
    shape.appendChild(subject);
    shape.appendChild(content);
    if(element.fin){
        shape.className="contents done";
    }else{
        shape.className="contents";
    }
    shape.addEventListener("click", finTodo);
    li.appendChild(shape);
    li.appendChild(delBtn);
    toDoList.appendChild(li);
}
function registTodo(sbj, cnt){
    fetch("http://localhost:8080/todo",{
        method :'POST',
        headers:{
            'Content-Type': 'application/json',
            'charset':'utf-8'
        }, body:JSON.stringify({subject:sbj, content:cnt})
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
    registTodo(currentSubject, currentContent);
}
function loadToDos(){
    fetch("http://localhost:8080/todo-list").then(function(response){
        return response.json();
    }).then(function(json){
        console.log(json);
        json.forEach(element=>{
            todos.set(element.id, element);
            afterResponse(element);
        })
    });
}
function find(event){
    event.preventDefault();
    const id = document.querySelector("#find-id").value;
    fetch("http://localhost:8080/todo/"+id).then(function(response){
        console.log(response);
        return response.json();
    }).then(function(json){
        if(json.id===null){
            alert("없는 글 입니다.");
        }
        console.log(json);
    });
}
function init(){
    loadToDos();
    toDoForm.addEventListener("submit", handleSubmit);
    const findSubmit = document.querySelector("#find");
    findSubmit.addEventListener("submit", find);
}
init();