import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/',
      redirect: '/home'
    },

    {
      path: '/home',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/users',
      name: 'users',
      component: () => import('../components/Users/ListUsers.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/reservation',
      name: 'reservations',
      component: () => import('../components/Reservations/listReservations.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/reservations/add',
      name: 'addReservation',
      component: () => import('../components/Reservations/addReservations.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/tours',
      name: 'tours',
      component: () => import('../components/Tours/listTours.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/tours/add',
      name: 'addTour',
      component: () => import('../components/Tours/addTour.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/extras',
      name: 'extras',
      component: () => import('../components/Extra/listExtra.vue'),
      meta: { requiresAuth: true }
    },

    {
      path: '/extras/add',
      name: 'addExtra',
      component: () => import('../components/Extra/addExtra.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../components/Login/LoginView.vue'),
    },
  ],
})

router.beforeEach((to, from, next) => {
  const role = sessionStorage.getItem('ROLE')
  console.log('Ruta solicitada:', to.path)
  console.log('Requiere auth:', to.meta.requiresAuth)
  console.log('Usuario autenticado:', role)

  if (to.meta.requiresAuth && !role) {
    console.log('Redirigiendo a login...')
    next('/login')
  } else {
    next()
  }
})

export default router
