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
      <AddLand />
    </div>
    <div id="addElement_id" class="modal">
          <AddElement :landid="selectedLand.id" />
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
            <b-nav-item :disabled="noTreePresent">Alberi</b-nav-item>
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
        <p>{{treeAdded}}</p>
        <p>{{selectedLand.id}}</p>
        <b-button
            onclick="document.getElementById('addElement_id').style.display='block'"
            variant="primary bg-success">
            Aggiungi elemento
        </b-button>
      </b-card>
    </div>
  </body>
</div>
</template>

<script>
import { AXIOS } from "./http-common";
import EventBus from '../eventBus'
import AddLand from "./AddLand";
import AddElement from "./AddElement";
import AddTree from "./AddTree";

export default {
  name: "service",
  data() {
    return {
      response: [],
      errors: [],
      modal: "addLand_id",
      modal: "addElement_id",
      accesso: null,
      lands: [],
      firstLand: null,
      treeAdded: null,
      noTreePresent: true,
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
      all: ["addLand_id", "addElement_id"]
    };
  },
  created() {
    AXIOS.get(`/lands`)
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
  updated() {
     console.log("Service has been updated");

     EventBus.$on('TREE_ADDED', (payload) => {
           this.treeAdded = payload;
         });

         if(this.treeAdded != null) {
            console.log("Tree added is not null");
            //var params = new URLSearchParams();
            //params.append("selectedland", this.selectedLand.name);
            //params.append("tree", this.treeAdded);

              AXIOS.post(`/addTreelist`, {
                        params: {
                            land: this.selectedLand.id,
                            tree: this.treeAdded
                        }})
                      .then(response => {
                        console.log("Response Data:" + response.data);
                        this.treeAdded = null;
                        this.noTreePresent = false;
                  }).catch(e => {
                        this.errors.push(e);
                  });
           } else {
                 console.log("treeAdded is null");
           }

  },
  mounted() {
    console.log("Service has been mounted");
  },
  components: {
    AddLand,
    AddElement
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
      window.location.href = "/#/";
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
.logout {
  width: auto;
}
</style>