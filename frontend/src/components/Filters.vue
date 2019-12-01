<template >
  <form class="modal-content" action>
    <span @click="close('treeFilters_id')" class="close" title="Close Modal">&times;</span>

    <div class="container">
    <br>
    <br>

      <hr />
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <b-button disabled onclick="document.getElementById('addTree_id').style.display='block'"
            class="btn-success btn-block">Aggiungi Ortaggio</b-button>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <b-button
            onclick="document.getElementById('addTree_id').style.display='block'"
            class="btn-success btn-block"
            value="Add Tree"
          >Aggiungi Albero</b-button>
        </div>
      </div>
    </div>

    <!-- Modals -->
   

  </form>
</template>


<script>
import { AXIOS } from "./http-common";
import AddTree from "./AddTree";
import { closeMixin } from '../close-mixin';

export default {
  name: "Filters",
  mixins: [closeMixin],
  data() {
    return {
      response: [],
      errors: [],
      land: {
        id: "",
        name: "",
        unity: "",
        height: "",
        width: "",
        gradient: "",
        composition: ""
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
   components: {
      
    },
  methods: {

  }
};
</script>


<style scoped>
body,
html {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  background: #e74c3c !important;
}

/* Set a style for all buttons */
button {
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity: 1;
}

/* Float cancel and signup buttons and add an equal width */
.signupbtn {
  width: 100%;
}
.container {
  padding: 16px;
}
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: black;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .signupbtn {
    width: 100%;
  }
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
  background-color: #474e5d65;
  padding-top: 0px;
}
/* Modal Content/Box */
.modal-content {
  top: 8%;
  background-color: #fefefe;
  margin: 5% auto 15% auto;
  border: 1px solid #888;
  width: 90%;
}

</style>
