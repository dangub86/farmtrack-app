import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
      farmer: null,
      selectedTree: null
  },
  mutations: {
      LOGIN : (state, id) => {
        state.farmer = id;
      },
      LOGOUT : state => {
        state.farmer = null;
      },
      SELECT_TREE : (state, id) => {
        state.selectedTree = id;
      }
  },
  actions: {
    SET_FARMER_ID : (context, payload) => {
      let idIsValid = payload != null; 
      if (idIsValid) {
        context.commit("LOGIN", payload);
        window.location.href= "/#/callservice";
      } else {
        window.location.href="/#/";
      }
      
   },
   LOGOUT : context => {
       context.commit("LOGOUT")
       window.location.href = "/#/";  
   },
   SELECT_TREE : (context, payload) => {
    context.commit("SELECT_TREE", payload);
}
  },
  getters: {},
});