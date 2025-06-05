// src/composables/useListUsers.ts
import { ref, onMounted } from 'vue'
import userService from '@/services/user.service'
import type { User } from '@/models/user'

export function UserComponent() {
  const users = ref<User[]>([])
  const loading = ref(false)
  const error = ref(null)

  const loadUsers = async () => {
    loading.value = true
    error.value = null
    try {
      const response = await userService.getusers()
      users.value = response.data
    } catch (err: any) {
      console.error('Error al cargar usuarios:', err)
      error.value = err
    } finally {
      loading.value = false
    }
  }

  onMounted(() => {
    loadUsers()
  })

  return {
    users,
    loading,
    error,
    reload: loadUsers,
  }
}
