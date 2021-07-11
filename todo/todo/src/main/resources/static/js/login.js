function login(event){
    event.preventDefault();
    
    const id =  document.querySelector("#id").value;
    const password =  document.querySelector("#password").value;
    const ele = {"id":id,"password":password};
    fetch("http://localhost:8080/login",
    {
        method:"POST",
        headers:{
            'Content-Type': 'application/json',
            'charset':'utf-8'
        },
        body:JSON.stringify(ele)
    }).then(function(res){
        return res.json();
    }).then(function(json){
        console.log(json);
    });
}
function checkSession(){
    fetch("http://localhost:8080/login")
    .then(function(response){
        console.log(response);
        console.log(response.json());
        console.log(response.body());
    });
}
function init(){
    const submitLogin = document.querySelector("#login");
    submitLogin.addEventListener("submit", login);

    const sessionCheckButton = document.querySelector("#checkSession");
    sessionCheckButton.addEventListener("click", checkSession);
}
init();
