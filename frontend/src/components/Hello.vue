<template>
<body class="bg">
<div class="bar text-center text-white display-4">FarmTrack</div>
  <div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3 text-center ">
        <form action method="">     
          <div class="form-login">
            <br>
            <h4>Login</h4>
            <br>

            <input
              name="utente"
              type=" text"
              v-model="service.utente"
              class="form-control input-sm"
              placeholder="username"
              required
            >
            
            <input
              name="password"
              type="password"
              v-model="service.password"
              @keydown.enter.prevent="addCategory"
              class="form-control input-sm chat-input"
              placeholder="password"
              required
            >
           <br>

             <button type="login" href class="btn btn-secondary btn-sm" onclick="document.getElementById('sign').style.display='block'">
                  Sign In
                  <i class="fa fa-sign-in"></i>
                </button>
            <br>
            <br>
            <div class="wrapper">
              <span class="group-btn">
                <button type="login" href class="btn btn-primary btn-md" @mouseenter="validator()" @click="startchecker()">
                  Login
                  <i class="fa fa-sign-in"></i>
                </button>
              </span>
            </div>
          </div>
        </form>
      </div>

    <div id="sign" class="modal">
      <Signin/>
    </div>


    </div>
  </div>
  <br>
  <br>
  <br>
  <br>
</body>
</template>


<script>
// import axios from 'axios'
import { AXIOS } from "./http-common";
import Signin from "./Signin";

export default {
  name: "service",

  data() {
    return {
      msg: "HowTo call REST-Services:",
      checking:null,
      params:null,
      accesso:null,
      answer:null,
      response: [],
      modal: "Signin",
      errors: [],
       service: {
          utente: '',
          password: ''
    },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
  }
},
  components: {
    Signin
  },
methods: {
      // Fetches posts when the component is created.
      startchecker(){
        if(this.checking===true){
          window.location.href= "/#/callservice?livello="+this.accesso;
        }
        else{
          //this.accesso=null;
          /* this.params=null; */
          window.location.href="/#/";
        }
        
      },
      validator () {
        this.params = new URLSearchParams()
        this.params.append('utente', this.service.utente)
        this.params.append('password', this.service.password)
        AXIOS.post(`/restlogin`, this.params)
          .then(response => {

            //.then(response => {
            // JSON responses are automatically parsed.
            this.answer = response.data
            console.log(this.answer+"This is answer from our checker")
            console.log('Check of enter')
            this.showResponse = true
             

            if(this.answer =="A" || this.answer == "B" || this.answer == "C"){
              console.log('This is the entrance to menu page')
              this.accesso=this.answer;
              //bus.$emit('form-submitted',this.accesso);
              
              this.checking=true;
              //window.location.href= "/#/callservice"
              
            }
            else{
              //this.params=null;
              this.checking=false;
              //window.location.href="/#/"
            }
            //this.startchecker();
            
          })
          .catch(e => {
            this.errors.push(e)
          })
     }
}
}

</script>

<style scoped>


input { 
	width: 100%; 
	margin-bottom: 10px; 
	background: rgba(0,0,0,0.3);
	border: none;
	outline: none;
	padding: 10px;
	font-size: 13px;
	color: #fff;
	text-shadow: 1px 1px 1px rgba(0,0,0,0.3);
	border: 1px solid rgba(0,0,0,0.3);
	border-radius: 4px;
	box-shadow: inset 0 -5px 45px rgba(100,100,100,0.2), 0 1px 1px rgba(255,255,255,0.2);
	-webkit-transition: box-shadow .5s ease;
	-moz-transition: box-shadow .5s ease;
	-o-transition: box-shadow .5s ease;
	-ms-transition: box-shadow .5s ease;
	transition: box-shadow .5s ease;
}
@media (max-width: 640px) {
  body {
    font-size: 1.3rem;
  }
  h1 {
    font-size: 16px;
  }
  h5 {
    font-size: 12;
  }
}
@media (max-width: 767px) {
  .desc {
    margin-top: 50px;
  }
  h5 {
    font-size: 8px;
  }
  .desc {
    width: 250px;
    min-height: 0px !important;
    margin-top: 50px;
  }
  .form-login {
    width: 250px;
  }
  .container {
    margin-right: 10px;
    padding: 50px !important;
  }
}

@media (min-width: 641px) {
  body {
    font-size: 1.2rem;
  }


  h5 {
    font-size: 14px;
  }
}

@media (min-width: 960px) {
  body {
    font-size: 1.4rem;
  }
   h5 {
    font-size: 20px;
  }
}

@media (min-width: 1100px) {
  body {
    font-size: 1.6rem;
  }
  h5 {
    font-size: 20px;
  }
  .desc {
    margin-left: 40px;
  }
}

h5.test {
  word-wrap: break-word;
}
body {
  height: 100%;
}
.uploading-image {
  display: flex;
}
.resized {
  width: auto;
  height: 150px;
}
.bg {
  background: lightgrey;
  max-height: 100vh !important;
  overflow: hidden !important;
}

.bar {
    height: 10vh;
    background: limegreen;
    font-weight: 800;
}

.container {
  padding: 110px;
}
::placeholder {
  /* Chrome, Firefox, Opera, Safari 10.1+ */
  color: #ffffff69 !important;
  opacity: 1; /* Firefox */
  font-size: 18px !important;
  font-weight: 200 !important;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
.form-login {
  background-color: rgba(0, 0, 0, 0.55);
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 15px;
  border-color: #d2d2d2;
  border-width: 5px;
  color: white;
  box-shadow: 0 1px 0 #cfcfcf;
}
.form-control {
  background: transparent !important;
  color: white !important;
  font-size: 18px !important;
}
h1 {
  color: white !important;
}
h5 {
  border: 0 solid #fff;
  border-bottom-width: 1px;
  padding-bottom: 10px;
  text-align: center;
}

.form-control {
  border-radius: 10px;
}
.text-white {
  color: white !important;
}

.wrapper {
  text-align: center;
}

.a {
  font-weight: bold;
  color: #2c3e50;
}
.desc {
  background-color: rgba(0, 0, 0, 0.55);
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 15px;
  border-color: #d2d2d2;
  border-width: 5px;
  color: white;
  box-shadow: 0 1px 0 #cfcfcf;
  min-height: 450px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 12.5%;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: #474e5d;
  padding-top: 0px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto;
  border: 1px solid #888;
  width: 80%;
}

</style>
