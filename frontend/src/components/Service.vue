<template>
<div class="servicemy">
  <!-- NavBar -->
  <div id="nav" class="flex bg-dark">
    <b-dropdown
      id="ddown-offset"
      offset="25"
      text="+"
      variant="btn-block text-white bg-success btn-lg"
      class="m-2 w-15"
      no-caret
    >
      <b-dropdown-item
        onclick="document.getElementById('addLand_id').style.display='block'"
        style="width:auto;"
        @click="closeAll()"
      >Aggiungi Terreno</b-dropdown-item>
    </b-dropdown>

    <b-button
      offset="25"
      text="Logout"
      variant=" btn-block"
      class="m-2 w-15 logout btn-block btn-lg"
      @click="logout()"
    >
      <i class="fas fa-sign-out-alt text-white"></i>
    </b-button>
  </div>

  <body>
    <!-- Modals -->
    <div id="addLand_id" class="modal">
      <AddLand :farmer="farmerId" />
    </div>
    <div id="addElement_id" class="modal">
          <AddElement v-if="treeAdded === null" :landid="selectedLand.id" />
    </div>
     <div id="trees_id" class="modal modal-max">
          <Trees :landid="selectedLand.id"/>
    </div>

    <!-- Land Selection -->
    <div class="input-group mb-3" v-if="lands.length === 1">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">Terreno</label>
      </div>
      <select class="custom-select" id="inputGroupSelect00">
        <option>{{ lands[0].name }}</option>
      </select>
    </div>

    <div class="input-group mb-3" v-if="lands.length > 1">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">Terreno</label>
      </div>
      <select class="custom-select" id="inputGroupSelect01" v-model="selectedLand">
        <option v-for="land in lands" :key="land.id" :selected="lands[0].name">{{ land.name }}</option>
      </select>
    </div>

    <!-- Land Details -->
    <div  v-if="lands.length > 0">
      <b-card :title="selectedLand.name" body-class="text-center" header-tag="nav">
        <template v-slot:header>
          <b-nav card-header tabs>
            <b-nav-item active>Dettagli</b-nav-item>
            <b-nav-item :disabled="!hasTrees" 
              @click="openTreeModal()">
              Elementi
            </b-nav-item>
          </b-nav>
        </template>

        <b-card-text class="text-left">
        <div style="text-align:center;"><i>Dettagli relativi al terreno selezionato.</i></div><br>
        <br>
        <span>
           <strong>H x W:</strong> {{selectedLand.height}} x {{selectedLand.width}} ({{selectedLand.unity == 1 ? 'm2' : 'ha'}}) <br>
           <strong>Inclinazione:</strong> {{selectedLand.gradient}}° <br>
           <strong>Composizione:</strong> Terreno prevalentemente {{selectedLand.composition}}.
        </span>
        </b-card-text>
        <p class="d-none">{{updated}}</p>
        <b-button
            onclick="document.getElementById('addElement_id').style.display='block'"
            variant="primary bg-success">
            Aggiungi elemento
        </b-button>
        {{farmerId}}
      </b-card>
    </div>
  </body>
</div>
</template>

<script>
import { AXIOS } from "./http-common";
import EventBus from '../eventBus';
import AddLand from "./AddLand";
import AddElement from "./AddElement";
import AddTree from "./AddTree";
import Trees from "./Trees";

export default {
  name: "service",
  data() {
    return {
      response: [],
      errors: [],
      modal: "addLand_id",
      modal: "addElement_id",
      modal: "trees_id",
      accesso: null,
      farmerId: null,
      lands: [],
      firstLand: null,
      treeAdded: null,
      updated: false,
      hasTrees: false,
      selectedLand: {
        id: null,
        unity: null,
        name: null,
        height: null,
        width: null,
        gradient: null,
        composition: null
      },
      fields: [
        {
          key: "name",
          label: "Nome",
          sortable: false
        }
      ],
      all: ["addLand_id", "addElement_id", "trees_id"]
    };
  },
  beforeCreate() {
    console.log("Service before create");
    EventBus.$on('FARMER', (payload) => {
           this.farmerId = payload.id;
         });
    console.log("id: " + this.farmerId);
  },
  created() {
  console.log("Service has been created");

    let params = new URLSearchParams();
    params.append("farmer", this.farmerId);

    AXIOS.post(`/lands`, params)
      .then(response => {
        this.lands = response.data;
        console.log(this.lands);
        if (this.lands.length == 1) {
          this.selectedLand.id = this.lands[0].id;
          this.selectedLand.name = this.lands[0].name;
          this.selectedLand.unity = this.lands[0].unity;
          this.selectedLand.height = this.lands[0].height;
          this.selectedLand.width = this.lands[0].width;
          this.selectedLand.gradient = this.lands[0].gradient;
          this.selectedLand.composition = this.lands[0].composition;
        }
      })
      .catch(e => {
        this.errors.push(e);
      });
  },
  beforeUpdate() {
     console.log("Service beforeUpdate");

     EventBus.$on('TREE_ADDED', (payload) => {
           this.treeAdded = payload.id;
           this.updated = payload.updated;
         });

         if(this.treeAdded != null) {
            console.log("Tree added is not null");
            let params = new URLSearchParams();
            params.append("land", this.selectedLand.id);
            params.append("tree", this.treeAdded);

              AXIOS.post(`/addTreelist`, params)
                      .then(response => {
                        console.log("Response Data:" + response.data);
                        this.treeAdded = null;
                  }).catch(e => {
                        this.errors.push(e);
                  });
           } else {
                 console.log("treeAdded is null");
           }

  },
  updated() {
    console.log("Service has been updated");

this.$eventHub.$on('FARMER', payload => {
          console.log('in here!');
           this.farmerId = payload.id;
         });
    console.log("id: " + this.farmerId);


            let params = new URLSearchParams();
            params.append("landid", this.selectedLand.id);

                  AXIOS.post(`/treesByLand`, params)
                          .then(response => {
                            let trees = response.data;
                            if (trees.length > 0) {
                              this.hasTrees = true;
                            }
                            EventBus.$emit('TREES', trees);
                            console.log(trees);
                      }).catch(e => {
                            this.errors.push(e);
                      });

  },
  components: {
    AddLand,
    AddElement,
    Trees
  },
  methods: {
    openTreeModal() {
      if (this.hasTrees) {
        document.getElementById('trees_id').style.display='block';
      }
        
    },
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
      window.location.href = "/#/";
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
.centerize {
  text-align: center !important;
}
.flex {
  display: flex;
  justify-content: space-between;
}
.addBtn {
  background: green;
  border-radius: 50%;
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
.modal-max {
  top: 0;
  background-color: #fefefe;
  margin: 0 !important;
  padding: 0 !important;
  border: 1px solid #888;
  width: 100%;
  height: 100%;
}

.logout {
  width: auto;
}
</style>