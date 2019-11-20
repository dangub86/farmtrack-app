import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Service from '@/components/Service'
import Land from '@/components/AddLand'
import Tree from '@/components/AddTree'
import Trees from '@/components/Trees'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/callservice',
      name: 'Service',
      component: Service
    },
    {
      path: '/land',
      name: 'Land',
      component: Land
    },
    {
      path: '/tree',
      name: 'Tree',
      component: Tree
    },
    {
      path: '/trees',
      name: 'Trees',
      component: Trees
    },

  ]

})