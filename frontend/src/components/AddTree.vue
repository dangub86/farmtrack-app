<template >
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Albero</h1>
      <hr />

      <label for="name">
        <b>Nome</b>
      </label>
      <br />
      <input
        type="text"
        v-model="tree.name"
        class="input_100"
        placeholder="nome"
        name="name"
        required
      />
      <br />
      <div class="form-group">
        <label for="genre">
          <b>Genere</b>
        </label>
        <b-form-select class="form-control" v-model="tree.genre" :options="options"></b-form-select>
      </div>

      <br />
      <label for="variety">
        <b>Varietà</b>
      </label>
      <br />
      <input
        type="numeric"
        v-model="tree.variety"
        class="input_100"
        placeholder="Varietà"
        name="variety"
        required
      />
       <br />
       <label for="age">
              <b>Età</b>
            </label>
            <br />
      <input
        type="numeric"
        v-model="tree.age"
        class="input_100"
        placeholder="Età"
        name="age"
        required
      />
      <br />
      <hr />
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button
            @click.prevent="addTree()"
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
import EventBus from '../eventBus';
import { closeMixin } from '../close-mixin';

export default {
  name: "tree",
  props: ["idland"],
  mixins: [closeMixin],
  data() {
    return {
      response: [],
      errors: [],
      success: false,
      error: false,
      message: "",
      tree: {
        id: "",
        name: "",
        genre: null,
        variety: "",
        age: ""
      },
      options: [
        {value: null, text: "Scegli..."},
        {value: "Ulivo", text: "Ulivo"},
        {value: "Pesco", text: "Pesco"}
      ],
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
  methods: {
  
    // Fetches posts when the component is created.
    addTree() {
      var params = new URLSearchParams();
      params.append("name", this.tree.name);
      params.append("genre", this.tree.genre);
      params.append("variety", this.tree.variety);
      params.append("age", this.tree.age);
      params.append("land", this.idland);

      AXIOS.post(`/addTree`, params)
        .then(response => {
          //JSON responses are automatically parsed.
          this.response = response.data;
          this.tree.id = response.data;
          console.log(response.data);
          this.showResponse = true;
          const payload = {
                    id: this.tree.id,
                    updated: true
            };
          EventBus.$emit('TREE_ADDED', payload);
          this.success = true;
          this.responseAfterSubmit();
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
</style>
