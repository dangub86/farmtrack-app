<template>
<body class="bg">

<div class="limiter">
 
		<div class="container-login100" style="background-image: url('images/bg-01.jpg');">
     <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					SIGN IN
				</span>
				<form class="login100-form validate-form p-b-33 p-t-5" action method="">

					<div class="wrap-input100" >
				<input
              name="name"
              type=" text"
              v-model="service.name"
              class="input100"
              placeholder="name"
              required
            >
						<span class="focus-input100" ></span>
					</div>

					<div class="wrap-input100 validate-input">
				<input
              name="surname"
              type=" text"
              v-model="service.surname"
              class="input100"
              placeholder="surname"
              required
            >
						<span class="focus-input100" ></span>
					</div>

          	<div class="wrap-input100 validate-input">
			 <input
              name="email"
              type=" text"
              v-model="service.email"
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
              v-model="service.password"
              @keydown.enter.prevent="addCategory"
              class="input100"
              placeholder="password"
              required
            >
						<span class="focus-input100" ></span>
					</div>

					<div class="container-login100-form-btn m-t-32">
						<button type="login" class="login100-form-btn" @click="signin()">
							Sign In
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</template>


<script>
// import axios from 'axios'
import { AXIOS } from "./http-common";
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
      errors: [],
       service: {
          name: '',
          surname: '',
          email: '',
          password: ''
    },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
  }
},
methods: {
   closeAll() {
      for (
        var i = 0;
        i < document.getElementsByClassName("modal").length;
        i++
      ) {
        document.getElementsByClassName("modal")[i].style.display = "none";
      }
    },
      // Fetches posts when the component is created.
      startchecker(){

      },
            signin() {
              this.params = new URLSearchParams()
              this.params.append('name', this.service.name)
              this.params.append('surname', this.service.surname)
              this.params.append('email', this.service.email)
              this.params.append('pwd', this.service.password)
              AXIOS.post(`/sign`, this.params)
                .then(response => {
                  //.then(response => {
                  // JSON responses are automatically parsed.
                  this.answer = response.data
                  console.log(this.answer+"This is answer from our checker")
                  console.log('Check of enter')
                  this.showResponse = true
                })
                .catch(e => {
                  this.errors.push(e)
                })
           }
      }
}
</script>

<style scoped>
/* The Close Button (x) */
.close {
  position: absolute;
  right: 30px;
  top: 5px;
  font-size: 40px;
  font-weight: bold;
  color: black;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}


</style>