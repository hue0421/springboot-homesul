import React, { useEffect } from 'react';
import './App.css';

function App() {

  let person = {
    username:"abcd",
    password:"1234"
  }

  useEffect(()=>{
    fetch("http://localhost:8000/loginProc",{
      method:"post",
      body: JSON.stringify(person),
      headers:{
        'Content-Type':"application/json; charset=utf-8"
      }
    }).then(res=>{
      console.log(1,res);
      for(let header of res.headers.entries()){
      if(header[0] ==="authorization"){
        localStorage.setItem("authorization",header[1]);
      }
      
    }
      return res.text();
    }).then(res=>{
      //if(res==="ok"){ // ==두개는 값비교 === 세개는 값과 타입비교
        console.log(3,res);
      
    });
    
    //localStorage.setItem("jwt","asdfabsdf");
    //let jwtToken = localStorage.getItem("jwt");
    //console.log(jwtToken);
  },[]);

  return (
   <div>1</div>
  );
}

export default App;
