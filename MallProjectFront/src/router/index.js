import { createRouter, createWebHistory } from 'vue-router'
import ViewProducts from '../views/ViewProducts.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ViewProducts
    },
    {
      path: '/add',
      name: 'add',
      component: () => import('../views/AddProducts.vue')
    },
    {
      path: '/update',
      name: 'update',
      component: ()=> import('../views/UpdateProducts.vue')
    },

    {
      path: '/orders',
      name: 'orders',
      component: ()=> import('../views/ViewOrders.vue')
    }

  ]
})

export default router
