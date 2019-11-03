<template>
  <div class="servicemy">
    <div id="nav" class="flex bg-dark">

 <b-dropdown
        id="ddown-offset"
        offset="25"
        text="+"
        variant=" btn-block"
        class="m-2 w-15 addBtn"
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
        class="m-2 w-15 logout btn-outline-light btn-block"
        @click="logout()"
      >Logout</b-button>

    </div>

<body>
    <div id="addLand_id" class="modal">
      <AddLand/>
    </div>

     <div class="input-group mb-3" v-if="lands.length === 1">
          <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">Terreno</label>
          </div>
      <select class="custom-select" id="inputGroupSelect00">
        <option>
          {{ lands[0].name }}
        </option>
      </select>
        </div>

    <div class="input-group mb-3" v-if="lands.length > 1">
      <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">Terreno</label>
      </div>
  <select class="custom-select" id="inputGroupSelect01" v-model="selectedLand">
    <option v-for="land in lands" :selected="lands[0].name">
      {{ land.name }}
    </option>
  </select>
    </div>


    </body>
  </div>
</template>

<script>
import { AXIOS } from "./http-common";
import AddLand from "./AddLand";
export default {
  name: "service",
  data() {
    return {
      response: [],
      errors: [],
      modal: "addLand_id",
      accesso: null,
      lands: [],
      firstLand: null,
      selectedLand: null,
      fields: [
                {
                  key: 'name',
                  label:'Nome',
                  sortable:false
                }
               ],
      all: ["addLand_id"]
    };
  },
  created() {
         AXIOS.get(`/lands`)
                 .then(response => {
                     this.lands=response.data;
                     console.log(this.lands);
                   })
                   .catch(e => {
                     this.errors.push(e)
                   })
    },
   components: {
    AddLand
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
  justify-content: space-between;
}
#ddown-offset {
    color: white !important;
}
.addBtn {
    background: green;
    border-radius: 50%;
}
#ddown-offset.dropdown-toggle::after {
   display: none;
   border-color: red;
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
  width: 80%;
}
.logout {
  width: auto;
}
</style>