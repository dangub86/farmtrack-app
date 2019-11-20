<template >
  <div class="container-fluid">
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
      <br />
      <br />
      <br>

      <b-button offset="25" text="Aggiorna" class="update-btn" @click="loadTrees">
        <i class="fas fa-sync-alt"></i>
      </b-button>
      <hr />

      <b-card-group class="card-horizontal align-self-center" >
        <b-card
          :title="tree.name"
          img-alt="Tree"
          class="m-1 tree-card"
          v-for="(tree, i) in currentPageItems"
          :key="i"
        >
          <img src="../assets/tree.webp" class="tree-img" />
        </b-card>
      </b-card-group>

    <br>
    <br>
    <div class="treePagination">
     <b-pagination
            v-model="currentPage"
            :total-rows="totalRows"
            :per-page="perPage"
            size="lg"
            pills
            align="center"
            class="overflow-auto"
          />
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
      currentPageIndex: 0,
      nbPages: 0
    };
  },
  computed: {
    totalRows() {
      return this.trees.length;
    },
    currentPageItems() {
      let lengthAll = this.trees.length;
      this.nbPages = 0;
      for (let i = 0; i < lengthAll; i = i + this.perPage) {
        this.paginated_items[this.nbPages] = this.trees.slice(
          i,
          i + this.perPage
        );

        this.nbPages++;
      }

      return this.paginated_items[this.currentPage - 1];
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
