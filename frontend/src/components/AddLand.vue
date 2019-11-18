<template >
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Terreno</h1>
      <hr />

      <label for="name">
        <b>Nome</b>
      </label>
      <br />
      <input
        type="text"
        v-model="land.name"
        class="input_100"
        placeholder="nome"
        name="name"
        required
      />
      <br />
      <div class="form-group">
        <label for="unity">
          <b>Dimensioni in:</b>
        </label>
        <select class="form-control" v-model="land.unity">
          <option value="1">m2</option>
          <option value="2">ha(ettaro)</option>
        </select>
      </div>

      <br />
      <label for="dim">
        <b>Dimensioni campo</b>
      </label>
      <br />
      <input
        type="numeric"
        v-model="land.height"
        style="width:40%;"
        class="input_100"
        placeholder="H"
        name="dim"
        required
      />
      <input
        type="numeric"
        v-model="land.width"
        style="width:40%;"
        class="input_100"
        placeholder="W"
        name="width"
        required
      />
      <br />
      <label for="gradient">
        <b>Inclinazione(°)</b>
      </label>
      <input
        type="numeric"
        v-model="land.gradient"
        class="input_100"
        placeholder="Inclinazione"
        name="gradient"
      />

      <label for="composition">
        <b>Composizione</b>
      </label>
      <div style="width:100%">
        <input
          type="text"
          v-model="land.composition"
          class="input_100"
          placeholder="Composizione"
          name="composition"
        />
      </div>

      <hr />

      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button
            type="submit"
            @click.prevent="addLand()"
            class="btn-success btn-block"
            value="Sign In"
          >Conferma</button>
        </div>
      </div>
    </div>
  </form>
</template>


<script>
import { AXIOS } from "./http-common";
export default {
  name: "land",
  props: ["farmer"],
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
    // Fetches posts when the component is created.
    addLand() {
      var params = new URLSearchParams();
      params.append("name", this.land.name);
      params.append("unity", this.land.unity);
      params.append("height", this.land.height);
      params.append("width", this.land.width);
      params.append("gradient", this.land.gradient);
      params.append("composition", this.land.composition);
      params.append("farmer", this.$store.state.farmer);

      AXIOS.post(`/addLand`, params)
        .then(response => {
          //JSON responses are automatically parsed.
          this.response = response.data;
          this.land.id = response.data;
          console.log(response.data);
          this.showResponse = true;
          this.closeAll();
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

/* Add a background color when the inputs get focus */
.testo:focus,
input[type="password"]:focus,
textarea[type="text"]:focus {
  background-color: #ddd;
  outline: none;
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

.sup {
  position: relative;
  bottom: 1ex;
  font-size: 80%;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn,
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
  .cancelbtn,
  .signupbtn {
    width: 100%;
  }
}
.input_100 {
  margin: 5px 10px 22px 0;
  width: 100%;
  padding: 15px;
  display: inline-block;
  border: none;
  background: #f1f1f1;
  background-color: #ddd;
  outline: none;
}
.numeri {
  margin: 5px 10px 22px 0 !important;
  width: 48% !important;
  padding: 15px !important;
  display: inline-block !important;
  border: none !important;
  border: none !important;
  background: #f1f1f1 !important;
}
.numeri:focus {
  background-color: #ddd;
  outline: none;
}
input[name="CF"] {
  width: 30% !important;
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 20px;
  display: inline-block;
  border: none;
  background: #f1f1f1;
  height: 50px;
}
input[name="CF"]:focus {
  background-color: #ddd;
  outline: none;
}
</style>


