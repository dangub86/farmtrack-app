import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
      farmer: null
  },
  mutations: {
      LOGIN : (state, id) => {
        state.farmer = id;
      },
      LOGOUT : state => {
        state.farmer = null;
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
      context.commit("LOGOUT");
   }
  },
  getters: {},
});