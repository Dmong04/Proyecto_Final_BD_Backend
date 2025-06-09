import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path:'/',
      redirect: '/home'

    },

    {
      path: '/home',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
    },

    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },

    {
      path: '/users',
      name: 'users',
      component: () => import('../components/Users/ListUsers.vue'),
    },

    {
      path:'/reservation',
      name:'reservations',
      component: () => import('../components/Reservations/listReservations.vue')
    },

    {
      path: '/reservations/add',
      name: 'addReservation',
      component: () => import('../components/Reservations/addReservations.vue'),
    },

    {
      path: '/tours',
      name: 'tours',
      component: () => import('../components/Tours/listTours.vue'),
    },

    {
      path: '/tours/add',
      name: 'addTour',
      component: () => import('../components/Tours/addTour.vue'),
    },

    {
      path: '/extras',
      name: 'extras',
      component: () => import('../components/Extra/listExtra.vue'),
    },

    {
      path: '/extras/add',
      name: 'addExtra',
      component: () => import('../components/Extra/addExtra.vue'),
    },


  ],
})

export default router
