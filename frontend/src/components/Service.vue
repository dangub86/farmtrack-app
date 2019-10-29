<template>
  <div class="servicemy">
    <div id="nav" class="flex bg-dark">

      <b-button
        offset="25"
        text="Logout"
        variant=" btn-block"
        class="m-2 w-15 logout btn-outline-light btn-block"
        @click="logout()"
      >Logout</b-button>

    </div>

<body>
     <div class="btn-group dropup">
       <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         Dropup
       </button>
       <div class="dropdown-menu">
         <!-- Dropdown menu links -->
         <ul>
         <li>Link 1</li>
         <li>Link 2</li>
         </ul>
       </div>
     </div>
    <div id="id01" class="modal">
      <RegistraUtente/>
    </div>
    <div id="id02" class="modal">
      <CambiaPassword/>
    </div>

    </body>
  </div>
</template>

<script>
import { AXIOS } from "./http-common";
import RegistraUtente from "./RegistraUtente";
import CambiaPassword from "./CambiaPassword";

export default {
  name: "service",

  data() {
    return {
      response: [],
      errors: [],
      modal: "id01",
      modal: "id02",
      accesso: null,
      all: ["id01", "id02"]
    };
  },
  created() {
    this.accesso = this.$route.query.livello;
  },
  //   beforeRouteEnter(to, from, next) {
  //   if (this.accesso != "") {
  //     next();
  //   }
  // },
  components: {
    RegistraUtente,
    CambiaPassword
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
    logout() {
      this.accesso = null;
      window.location.href="/#/";
    },
    // Fetches posts when the component is created.
    callRestService() {
      AXIOS.get(`/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    onclick: function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }
  }
};
</script>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.flex {
  display: flex;
  justify-content: center;
}

.test > .btn-group > .dropdown-menu:active {
  display: block;
  margin-bottom: 5px;
  position: static;
}
#ddown-offset {
  height: 50px;
}
.dropup {
    position: absolute;
    bottom: 1vh;
    right: 8vw;
}
body {
  margin-top: 2%;
}
.fa-fw {
  width: 2em;
}
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
body {
  font-family: Arial, Helvetica, sans-serif;
  size: 100%;
}
* {
  box-sizing: border-box;
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

.logout {
  width: auto;
}
</style>
