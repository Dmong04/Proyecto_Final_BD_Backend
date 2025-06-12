import api from '@/api/api'

export default {
  getusers() {
    return api.get('/user')
  },

  findUser(username: string) {
    return api.get(`/user/${username}`)
  },

  createUser(userData: {
    email: string
    username: string
    password: string
    clientId?: number
    adminId?: number
  }) {
    return api.post('/user', userData)
  },

  updateUser(id: number, userData: { email: string; username: string }) {
    return api.put(`/user/${id}`, userData)
  },

  deleteUser(id: number) {
    return api.delete(`/user/${id}`)
  },
}
