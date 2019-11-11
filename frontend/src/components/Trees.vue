<template >
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>

    <div class="container">
    <br>
    <br>
<!--
    <div class="input-group mb-3" v-if="trees.length > 1">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">Terreno</label>
      </div>
      <select class="custom-select" id="inputGroupSelect01" v-model="selectedTree">
        <option v-for="tree in trees" :key="tree.id" :selected="trees[0].name">{{ tree.name }}</option>
      </select>
    </div>
-->
      <hr />
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <b-button disabled onclick="document.getElementById('addTree_id').style.display='block'"
            class="btn-success btn-block">Aggiungi Ortaggio</b-button>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <b-button
            @click="loadTrees"
            class="btn-success btn-block"
            value="Add Tree"
          >Aggiorna</b-button>
           <p v-for="tree in trees" :key="tree.id">{{tree.name}}</p>
        </div>
      </div>
    </div>


  </form>
</template>


<script>
import { AXIOS } from "./http-common";
import EventBus from '../eventBus'

export default {
  name: "Trees",
  props: ["landid"],
  data() {
    return {
      response: [],
      errors: [],
      trees: [],
      selectedTree: null,
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
  created() {
      console.log("Trees has been created");
      EventBus.$on('TREES', (trees) => {
           this.trees = trees;
         });
      
    },
  methods: {
    loadTrees() {
        let params = new URLSearchParams;
       params.append("landid", this.landid);

    AXIOS.post(`/treesByLand`, params)
      .then(response => {
        this.trees = response.data;
        console.log(this.trees);
     
      })
      .catch(e => {
        this.errors.push(e);
      });
    },
    closeAll() {
      for (
        var i = 0;
        i < document.getElementsByClassName("modal").length;
        i++
      ) {
        document.getElementsByClassName("modal")[i].style.display = "none";
      }
    }
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
