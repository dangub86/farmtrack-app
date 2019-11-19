<template >
  <div class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>

    <div class="container">
      <br />
      <br />

      <b-button offset="25" text="Aggiorna" class="update-btn" @click="loadTrees">
        <i class="fas fa-sync-alt"></i>
      </b-button>
      <hr />


      <b-card-group 
        id="treeCardGroup"
        class="card-horizontal" 
        v-for="(tree, index) in currentPageItems" :key="index"
      >
        
        <b-card
          :title="tree.name"
          img-alt="Tree"
          class="m-1 tree-card"
         >
        
          <img src="../assets/tree.webp" class="tree-img" />
        </b-card>
      </b-card-group>

     

 
 <b-row>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0" />
      </b-col>
    </b-row>

    
    </div>
  </div>
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
      showRetrievedUser: false,
      
      currentPage: 1,
      perPage: 8,
      paginated_items: {},
      currentPageIndex:0,
      nbPages:0
    };
  },
  computed: {
    formattedTrees() {
      return this.trees.reduce((c, n, i) => {
        if (i % 2 === 0) c.push([]);
        c[c.length - 1].push(n);
        return c;
      }, []);
    },
    totalRows() {
      return this.trees.length;
    },
      pageCount() {
      let l = this.totalRows,
        s = this.perPage;
      return Math.floor(l / s);
    },
      currentPageItems() {
          let lengthAll =this.trees.length;
      this.nbPages = 0;
        for (let i = 0; i < lengthAll; i = i + this.perPage) {
        this.paginated_items[this.nbPages] = this.trees.slice(
          i,
          i + this.perPage
        );
        this.nbPages++;
      }
       return this.paginated_items[this.currentPage-1];
  
    }
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
  width: 40vw;
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

.card-horizontal.card-group {
  flex-direction: row;
}

</style>
