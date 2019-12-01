<template >
  <div class="container-fluid">

<b-container class="icons">
  <b-row class="flex">
    <b-col>
        <b-button text="Aggiorna" class="icon-btn" @click="loadTrees()">
                   <i class="fas fa-sync-alt"></i>
        </b-button>
    </b-col>
    <b-col>
    <b-button text="Filters" class="icon-btn" @click="openFiltersModal()">
                       <i class="fas fa-sliders-h"></i>
            </b-button>
      </b-col>
    <b-col>
    <b-button text="Close" class="icon-btn" @click="closeAll()">
                           <i class="fas fa-times"></i>
                </b-button>
      </b-col>
  </b-row>
</b-container>


    <!-- Searchbar -->
        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
              <div class="searchbar">
                <input  class="search_input" type="text" name="" placeholder="Search...">
                <a  class="search_icon"><i class="fas fa-search"></i></a>
              </div>
            </div>
          </div>

      <hr />

<!-- Modals -->
    <div id="treeDetails_id" class="modal">
      <TreeDetails :treeId="selectedTree" />
    </div>

    <div id="treeFilters_id" class="modal">
      <Filters :treeId="selectedTree" />
    </div>

      <b-card-group class="card-horizontal align-self-center" >
        <b-card
          :title="tree.name"
          img-alt="Tree"
          class="m-1 tree-card"
          v-for="(tree, i) in currentPageItems"
          :key="i"
          @click="openTreeModal(tree.id)"
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
import TreeDetails from "./TreeDetails";
import Filters from "./Filters";

export default {
  name: "Trees",
  props: ["landid"],
  data() {
    return {
      response: [],
      errors: [],
      trees: [],
      modal: "treeDetails_id",
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
   components: {
    TreeDetails,
    Filters
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
    openTreeModal(id) {
        this.selectedTree = id;
         document.getElementById('treeDetails_id').style.display='block';
    },
    openFiltersModal() {
         document.getElementById('treeFilters_id').style.display='block';
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
.flex {
  display: flex;
  justify-content: space-around;
}
.icons .col {
    flex-grow: 0;
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

.icon-btn {
  justify-content: space-around;
  background-color: rgb(110, 110, 110) !important;
  border-radius: 25px;
  opacity: 0.6;
}

.tree-card {
  width: 43.5vw;
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

 .searchbar{
    margin-bottom: auto;
    margin-top: auto;
    height: 60px;
    background-color: #353b48;
    border-radius: 30px;
    padding: 10px;
    }

    .search_input{
    color: white;
    border: 0;
    outline: 0;
    background: none;
    width: 0;
    caret-color:transparent;
    line-height: 40px;
    transition: width 0.4s linear;
    }

    .searchbar> .search_input{
    padding: 0 10px;
    width: 85vw;
    caret-color:red;
    transition: width 0.4s linear;
    }
    .searchbar > .search_icon{
    background: white;
    color: #e74c3c;
    }

    .search_icon{
    height: 40px;
    width: 45px;
    float: right;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    color:white;
    border: 1px solid #353b48;
    margin: -10px;
    }

    ::placeholder {
      /* Chrome, Firefox, Opera, Safari 10.1+ */
      color: #ffffff !important;
      opacity: 1; /* Firefox */
      font-size: 20px !important;
    }

</style>
