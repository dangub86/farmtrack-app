<template >
  <div class="container-fluid modal-content">
        <span @click="close('treeDetails_id')" class="close" title="Close Modal">&times;</span>
      <br />
      <br />
      <br>
  <h1><strong>{{tree.name}}</strong></h1>

     <br />
      <br />
      <br>

       <span>
           <strong>NOME:</strong> {{tree.name}}  <br>
           <strong>GENRE:</strong> {{tree.genre}} <br>
           <strong>VARIETY:</strong>{{tree.variety}} <br>
           <strong>AGE:</strong> {{tree.age}} <br>.
        </span>
      <!-- Tree Details 
    <div  v-if="tree != null">
      <b-card :title="this.tree.name" body-class="text-center" header-tag="nav">
        <template v-slot:header>
          <b-nav card-header tabs>
            <b-nav-item active>Dettagli</b-nav-item>
          </b-nav>
        </template>

        <b-card-text class="text-left">
        <div style="text-align:center;"><i>Dettagli relativi all'albero selezionato.</i></div><br>
        <br>
        <span>
           <strong>NOME:</strong> {{tree.name}}  <br>
           <strong>GENRE:</strong> {{tree.genre}} <br>
           <strong>VARIETY:</strong>{{tree.variety}}
           <strong>AGE:</strong> {{tree.age}} <br>.
        </span>
        </b-card-text>
             {{this.treeId}}
      </b-card>
    </div>
-->
    </div>
 
</template>


<script>
import { AXIOS } from "./http-common";
import EventBus from "../eventBus";
import { closeMixin } from '../close-mixin';

export default {
  name: "TreeDetails",
  props: ["treeId"],
  mixins: [closeMixin],
  data() {
    return {
      response: [],
      errors: [],
      trees: [],
      selectedTree: null,
      tree: {
        id: null,
        name: null,
        genre: null,
        variety: null,
        age: null
      },
    };
  },
  computed: {
   
  },
  created() {
    console.log("TreeDetails has been created");
    this.populate();
  },
  mounted() {
    console.log("TreeDetails has been mounted");
    this.populate();
  },
  updated() {
      console.log("TreeDetails has been updated");
      this.populate();
  },
  methods: {
    populate() {
       let params = new URLSearchParams();
       params.append("ID", this.treeId);

    AXIOS.post(`/treeDetails`, params)
      .then(response => {
        let responseTree = response.data;
        this.selectedTree = responseTree.id
        this.tree.name = responseTree.name;
        this.tree.genre = responseTree.genre;
        this.tree.variety = responseTree.variety;
        this.tree.age = responseTree.age;
        console.log(responseTree);
     
      })
      .catch(e => {
        this.errors.push(e);
      });
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

.update-btn {
  position: absolute;
  top: 5vh;
  left: 5vw;
  background-color: rgb(110, 110, 110) !important;
  border-radius: 25px;
  opacity: 0.6;
}

.tree-card {
  width: 43.8vw;
  justify-content: space-around;
  box-shadow:  1px 1px 1px rgba(85, 85, 85, 0.651),  -1px -1px 1px rgba(85, 85, 85, 0.651);
}

.tree-card .card-title {
  position: absolute;
  bottom: 5%;
  right: 0;
  background-color: rgba(85, 85, 85, 0.651);
  color: white;
  padding: 5% 20%;
}

.tree-img {
  max-width: 100%;
  max-height: 100%;
}

.card-horizontal {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin-bottom: 15%;
}

.treePagination {
  position: absolute;
  left: 0 !important;
  margin-bottom: 1vh !important;
  padding: 0;
}

.pagination-lg  {
  box-shadow: -5px 2px 3px lime, -5px -2px 3px lime;
  padding-bottom: 3%;
  padding-top: 3%;
  margin-bottom: 0;
}


</style>
