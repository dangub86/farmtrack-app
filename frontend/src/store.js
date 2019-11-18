import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
      farmer: null
  },
  mutations: {
      login(state, id) {
        state.farmer = id;
      }
  },
  actions: {},
  getters: {},
});