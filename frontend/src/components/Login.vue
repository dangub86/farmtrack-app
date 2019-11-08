<template>
<div class="servicemy">
<div id="nav" class="flex bg-dark">
    <b-dropdown
      id="ddown-offset"
      offset="25"
      text="+"
      variant="btn-block text-white bg-success btn-lg"
      class="m-2 w-15"
      no-caret
    >
      <b-dropdown-item
        onclick="document.getElementById('addLand_id').style.display='block'"
        style="width:auto;"
        @click="closeAll()"
      >Aggiungi Terreno</b-dropdown-item>
    </b-dropdown>

    <b-button
      offset="25"
      text="Logout"
      variant=" btn-block"
      class="m-2 w-15 logout btn-block btn-lg"
      @click="logout()"
    >
      <i class="fas fa-sign-out-alt text-white"></i>
    </b-button>
  </div>

<body class="bg">

<div id="sign" class="modal">
      <Signin/>
    </div>

<div class="limiter">
		<div class="container-login100" style="background-image: url('images/bg-01.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					FarmTrack Login
				</span>
				<form class="login100-form validate-form p-b-33 p-t-5">

					<div class="wrap-input100 validate-input" >
					<input
              name="email"
              type=" text"
              v-model="login.email"
              class="input100"
              placeholder="email"
              required
            >
						<span class="focus-input100" ></span>
					</div>

					<div class="wrap-input100 validate-input">
						<input
              name="password"
              type="password"
              v-model="login.password"
              @keydown.enter.prevent="addCategory"
              class="input100"
              placeholder="password"
              required
            >
						<span class="focus-input100" ></span>
					</div>

					<div class="container-login100-form-btn m-t-32">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>



<!--
  <div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3 text-center ">
        <form action method="">     
          <div class="form-login">
            <br>
            <h4>Login</h4>
            <br>

            <input
              name="email"
              type=" text"
              v-model="login.email"
              class="form-control input-sm"
              placeholder="email"
              required
            >
            
            <input
              name="password"
              type="password"
              v-model="login.password"
              @keydown.enter.prevent="addCategory"
              class="form-control input-sm chat-input"
              placeholder="password"
              required
            >
           <br>

             <button href class="btn btn-secondary btn-sm" onclick="document.getElementById('sign').style.display='block'">
                  Sign In
                </button>
            <br>
            <br>
            <div class="wrapper">
              <span class="group-btn">
                <button type="login" href class="btn btn-primary btn-md" @mouseenter="validator()" @click="startchecker()">
                  Login
                </button>
              </span>
            </div>
          </div>
        </form>
      </div>

    


    </div>
  </div>
  <br>
  <br>
  <br>
  <br>
  -->
</body>
</div>
</template>


<script>
// import axios from 'axios'
import { AXIOS } from "./http-common";
import Signin from "./Signin";

export default {
  name: "login",

  data() {
    return {
      msg: "HowTo call REST-Services:",
      checking: false,
      params:null,
      id:null,
      answer:null,
      response: [],
      modal: "Signin",
      errors: [],
       login: {
          email: '',
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
          window.location.href= "/#/callservice?id="+this.id;
        }
        else{
          window.location.href="/#/";
        }
        
      },
      validator () {
        this.params = new URLSearchParams()
        this.params.append('email', this.login.email)
        this.params.append('password', this.login.password)
        AXIOS.post(`/login`, this.params)
          .then(response => {
            this.answer = response.data
            console.log(response.data)
            console.log(this.answer + " -> This is answer from our checker")
            this.showResponse = true

            if(this.answer != ""){
              console.log('This is the entrance to menu page')
              this.id=this.answer
              this.checking=true
            }
            else{
              console.log('checking false')
              this.checking=false;
            }
            
          })
          .catch(e => {
            this.errors.push(e)
          })
     }
}
}

</script>

<style scoped>

.flex {
  display: flex;
  justify-content: space-between;
}

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



/*//////////////////////////////////////////////////////////////////
[ RESTYLE TAG ]*/

* {
	margin: 0px; 
	padding: 0px; 
	box-sizing: border-box;
}

body, html {
	height: 100%;
	font-family: Ubuntu-Regular, sans-serif;
}

/*---------------------------------------------*/
a {
	font-family: Ubuntu-Regular;
	font-size: 14px;
	line-height: 1.7;
	color: #666666;
	margin: 0px;
	transition: all 0.4s;
	-webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
}

a:focus {
	outline: none !important;
}

a:hover {
	text-decoration: none;
  color: #fff;
}

/*---------------------------------------------*/
h1,h2,h3,h4,h5,h6 {
	margin: 0px;
}

p {
	font-family: Ubuntu-Regular;
	font-size: 14px;
	line-height: 1.7;
	color: #666666;
	margin: 0px;
}

ul, li {
	margin: 0px;
	list-style-type: none;
}


/*---------------------------------------------*/
input {
	outline: none;
	border: none;
}

textarea {
  outline: none;
  border: none;
}

textarea:focus, input:focus {
  border-color: transparent !important;
}

input:focus::-webkit-input-placeholder { color:transparent; }
input:focus:-moz-placeholder { color:transparent; }
input:focus::-moz-placeholder { color:transparent; }
input:focus:-ms-input-placeholder { color:transparent; }

textarea:focus::-webkit-input-placeholder { color:transparent; }
textarea:focus:-moz-placeholder { color:transparent; }
textarea:focus::-moz-placeholder { color:transparent; }
textarea:focus:-ms-input-placeholder { color:transparent; }

input::-webkit-input-placeholder { color: #555555;}
input:-moz-placeholder { color: #555555;}
input::-moz-placeholder { color: #555555;}
input:-ms-input-placeholder { color: #555555;}

textarea::-webkit-input-placeholder { color: #555555;}
textarea:-moz-placeholder { color: #555555;}
textarea::-moz-placeholder { color: #555555;}
textarea:-ms-input-placeholder { color: #555555;}

label {
  margin: 0;
  display: block;
}

/*---------------------------------------------*/
button {
	outline: none !important;
	border: none;
	background: transparent;
}

button:hover {
	cursor: pointer;
}

iframe {
	border: none !important;
}

/*//////////////////////////////////////////////////////////////////
[ Utility ]*/
.txt1 {
  font-family: Poppins-Regular;
  font-size: 13px;
  color: #e5e5e5;
  line-height: 1.5;
}


/*//////////////////////////////////////////////////////////////////
[ login ]*/

.limiter {
  width: 100%;
  margin: 0 auto;
}

.container-login100 {
  width: 100%;  
  min-height: 100vh;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  padding: 15px;

  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  position: relative;
  z-index: 1;  
}

.container-login100::before {
  content: "";
  display: block;
  position: absolute;
  z-index: -1;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0,0,0,0.65);
}

.wrap-login100 {
  width: 390px;
  border-radius: 10px;
  overflow: hidden;

  background: transparent;
}


/*------------------------------------------------------------------
[ Form ]*/

.login100-form {
  width: 100%;
  border-radius: 10px;
  background-color: #fff;
}

.login100-form-logo {
  font-size: 60px; 
  color: #333333;

  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background-color: #fff;
  margin: 0 auto;
}

.login100-form-title {
  font-family: Ubuntu-Bold;
  font-size: 28px;
  color: #fff;
  line-height: 1.2;
  text-align: center;
  text-transform: uppercase;

  display: block;
}


/*------------------------------------------------------------------
[ Input ]*/

.wrap-input100 {
  width: 100%;
  position: relative;
  border-bottom: 1px solid #e6e6e6;
  padding: 29px 0;
}

.input100 {
  font-family: Ubuntu-Regular;
  font-size: 20px;
  color: #555555;
  line-height: 1.2;

  display: block;
  width: 100%;
  height: 50px;
  background: transparent;
  padding: 0 10px 0 80px;
  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

/*---------------------------------------------*/ 
.focus-input100 {
  position: absolute;
  display: block;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  pointer-events: none;
}

.focus-input100::before {
  content: "";
  display: block;
  position: absolute;
  bottom: -1px;
  left: 0;
  width: 0;
  height: 1px;

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;

  background: #d41872;
  background: -webkit-linear-gradient(left, #a445b2, #d41872, #fa4299);
  background: -o-linear-gradient(left, #a445b2, #d41872, #fa4299);
  background: -moz-linear-gradient(left, #a445b2, #d41872, #fa4299);
  background: linear-gradient(left, #a445b2, #d41872, #fa4299);
}

.focus-input100::after {
  font-family: Linearicons-Free;
  font-size: 18px;
  color: #999999;

  content: attr(data-placeholder);
  display: block;
  width: 100%;
  position: absolute;
  top: 40px;
  left: 35px;
 

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.input100:focus {
  padding-left: 60px;
}

.input100:focus + .focus-input100::after {
  left: 23px;
  color: #d41872;
}

.input100:focus + .focus-input100::before {
  width: 100%;
}

.has-val.input100 + .focus-input100::after {
  left: 23px;
  color: #d41872;
}

.has-val.input100 + .focus-input100::before {
  width: 100%;
}

.has-val.input100 {
  padding-left: 60px;
}


/*------------------------------------------------------------------
[ Button ]*/
.container-login100-form-btn {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.login100-form-btn {
  font-family: Ubuntu-Bold;
  font-size: 18px;
  color: #fff;
  line-height: 1.2;
  text-transform: uppercase;

  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 20px;
  min-width: 160px;
  height: 42px;
  border-radius: 21px;

  background: #d41872;
  background: -webkit-linear-gradient(left, #a445b2, #d41872, #fa4299);
  background: -o-linear-gradient(left, #a445b2, #d41872, #fa4299);
  background: -moz-linear-gradient(left, #a445b2, #d41872, #fa4299);
  background: linear-gradient(left, #a445b2, #d41872, #fa4299);
  position: relative;
  z-index: 1;

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.login100-form-btn::before {
  content: "";
  display: block;
  position: absolute;
  z-index: -1;
  width: 100%;
  height: 100%;
  border-radius: 21px;
  background-color: #555555;
  top: 0;
  left: 0;
  opacity: 0;

  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.login100-form-btn:hover {
  background-color: transparent;
}

.login100-form-btn:hover:before {
  opacity: 1;
}



/*------------------------------------------------------------------
[ Alert validate ]*/

.validate-input {
  position: relative;
}

.alert-validate::before {
  content: attr(data-validate);
  position: absolute;
  max-width: 70%;
  background-color: #fff;
  border: 1px solid #c80000;
  border-radius: 2px;
  padding: 4px 25px 4px 10px;
  top: 50%;
  -webkit-transform: translateY(-50%);
  -moz-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  -o-transform: translateY(-50%);
  transform: translateY(-50%);
  right: 10px;
  pointer-events: none;

  font-family: Ubuntu-Bold;
  color: #c80000;
  font-size: 13px;
  line-height: 1.4;
  text-align: left;

  visibility: hidden;
  opacity: 0;

  -webkit-transition: opacity 0.4s;
  -o-transition: opacity 0.4s;
  -moz-transition: opacity 0.4s;
  transition: opacity 0.4s;
}

.alert-validate::after {
  content: "\f12a";
  font-family: FontAwesome;
  font-size: 16px;
  color: #c80000;

  display: block;
  position: absolute;
  top: 50%;
  -webkit-transform: translateY(-50%);
  -moz-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  -o-transform: translateY(-50%);
  transform: translateY(-50%);
  right: 15px;
}

.alert-validate:hover:before {
  visibility: visible;
  opacity: 1;
}

@media (max-width: 992px) {
  .alert-validate::before {
    visibility: visible;
    opacity: 1;
  }
}


</style>
