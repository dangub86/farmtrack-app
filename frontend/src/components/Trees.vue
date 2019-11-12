<template >
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>

    <div class="container">
      <br />
      <br />
 
      <b-button offset="25" text="Aggiorna" class="update-btn" @click="loadTrees">
        <i class="fas fa-sync-alt"></i>
      </b-button>
      <hr />
      <div class="row">
        <div>
          <b-card
            v-for="tree in trees"
            :key="tree.id"
            :title="tree.name"
            img-alt="Tree"
            tag="article"
            class="ml-3 tree-card"
          >
          <img src="../assets/tree.webp" class="tree-img" style="max-width: 5rem;" />
         <p>{{tree.genre}} <span>({{tree.variety}})</span></p>
          <br>
            <b-card-text>{{tree.name}}</b-card-text>

            <b-button href="#" variant="primary">Edit</b-button>
          </b-card>
        </div>
      </div>
    </div>
  </form>
</template>


<script>
import { AXIOS } from "./http-common";
import EventBus from "../eventBus";

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
    EventBus.$on("TREES", trees => {
      this.trees = trees;
    });
  },
  methods: {
    loadTrees() {
      let params = new URLSearchParams();
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

.update-btn {
  position: absolute;
  top: 5vh;
  left: 5vw;
  background-color: rgb(110, 110, 110) !important;
  border-radius: 25px;
  opacity: 0.6;
}

.tree-card {

  width: 80vw;
}

.tree-img {
  position: absolute;
  top: 0;
  right: 0;
}
</style>
