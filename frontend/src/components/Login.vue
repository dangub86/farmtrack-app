<template>
<div class="servicemy">
<div id="nav" class="container-login100-form-btn m-t-32">
    <b-button
      offset="25"
      text="Sign In"
      class="login100-form-btn signin-btn"
      onclick="document.getElementById('sign').style.display='block'"
    > Sign In
    </b-button>
  </div>

<body class="bg">

<div class="limiter">
		<div class="container-login100" style="background-image: url('images/bg-01.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					FarmTrack Login
				</span>
				<form class="login100-form validate-form p-b-33 p-t-5" action method="">

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
              @input="validator()"
              class="input100"
              placeholder="password"
              required
            >
						<span class="focus-input100" ></span>
					</div>

					<div class="container-login100-form-btn m-t-32">
						<button 
              type="login" 
              class="login100-form-btn" 
              @click="startchecker()" 
              :disabled="!checking">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>

<div id="sign" class="modal">
      <Signin/>
</div>

</body>
</div>
</template>


<script>
import { AXIOS } from "./http-common";
import EventBus from '../eventBus';
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
        const payload = {
                    id: "this.id"
            };
          //this.$eventHub.$emit('FARMER', payload);

        if(this.checking===true){
          window.location.href= "/#/callservice?id=" +this.id;
          this.$state.commit('login', this.id);
        }
        else{
          window.location.href="/#/";
        }
        
      },
      validator () {
        console.log("Validating")
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

<style>

.flex {
  display: flex;
  justify-content: space-between;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
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
  margin: 20px auto;
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
  background-color:whitesmoke;
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
  background-color: rgba(51, 51, 51, 0.787);
}

.login100-form-logo {
  font-size: 60px; 
  color: #c5bebe;

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
  color: rgb(53, 53, 53);
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
  color: #4d4d4d;
  line-height: 1.2;

  display: block;
  width: 100%;
  height: 70px;
  background: white;
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
  height: 80px;
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

.login100-form-btn:disabled { 
  background: transparent;
  opacity: .3;
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
  margin-top: 10px;
  margin-bottom: 10px;
  min-width: 160px;
  height: 42px;
  border-radius: 5px;

  background: #0bbe50e8;
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

.signin-btn {
  color: #0bbe50e8 !important;
  background-color: #ececec !important;
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
