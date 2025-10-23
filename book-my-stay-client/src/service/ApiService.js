import axios from "axios"

export default class ApiService {
    static BASE_URL = "http://localhost:8080"

    static getHeader() {
        const token = localStorage.getItem("token");
        return {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
        };
    }

    static async registerUser(registration) {
        const response = await axios.post(`${this.BASE_URL}/auth/signup`, registration)
        return response.data
    }

    static async loginUser(loginDetails) {
        const response = await axios.post(`${this.BASE_URL}/auth/signin`, loginDetails)
        return response.data
    }

    static async getAllUsers() {
        const response = await axios.get(`${this.BASE_URL}/users`, {
            headers: this.getHeader()
        })
        return response.data
    }

    static async getUserProfile() {
        const response = await axios.get(`${this.BASE_URL}/users/me`, {
            headers: this.getHeader()
        })
        return response.data
    }

    static async getUser(userId) {
        const response = await axios.get(`${this.BASE_URL}/users/${userId}`, {
            headers: this.getHeader()
        })
        return response.data
    }

    static async getUserBookings(userId) {
        const response = await axios.get(`${this.BASE_URL}/users/${userId}/bookings`, {
            headers: this.getHeader()
        })
        return response.data
    }

    static async deleteUser(userId) {
        const response = await axios.delete(`${this.BASE_URL}/users/${userId}`, {
            headers: this.getHeader()
        })
        return response.data
    }

    static async addRoom(formData) {
        const result = await axios.post(`${this.BASE_URL}/rooms`, formData, {
            headers: {
                ...this.getHeader(),
                'Content-Type': 'multipart/form-data'
            }
        });
        return result.data;
    }

    static async getAllAvailableRooms() {
        const result = await axios.get(`${this.BASE_URL}/rooms/available`)
        return result.data
    }


    static async getAvailableRoomsByDateAndType(checkInDate, checkOutDate, roomType) {
        const result = await axios.get(
            `${this.BASE_URL}/rooms/available?checkInDate=${checkInDate}
		&checkOutDate=${checkOutDate}&roomType=${roomType}`
        )
        return result.data
    }

    static async getRoomTypes() {
        const response = await axios.get(`${this.BASE_URL}/rooms/types`)
        return response.data
    }

    static async getAllRooms() {
        const result = await axios.get(`${this.BASE_URL}/rooms`)
        return result.data
    }

    static async getRoomById(roomId) {
        const result = await axios.get(`${this.BASE_URL}/rooms/${roomId}`)
        return result.data
    }


    static async deleteRoom(roomId) {
        const result = await axios.delete(`${this.BASE_URL}/rooms/${roomId}`, {
            headers: this.getHeader()
        })
        return result.data
    }

    static async updateRoom(roomId, formData) {
        const result = await axios.put(`${this.BASE_URL}/rooms/${roomId}`, formData, {
            headers: {
                ...this.getHeader(),
                'Content-Type': 'multipart/form-data'
            }
        });
        return result.data;
    }

    static async bookRoom(roomId, userId, booking) {

        console.log("USER ID IS: " + userId)

        const response = await axios.post(`${this.BASE_URL}/bookings/${roomId}/users/${userId}`, booking, {
            headers: this.getHeader()
        })
        return response.data
    }


    static async getAllBookings() {
        const result = await axios.get(`${this.BASE_URL}/bookings`, {
            headers: this.getHeader()
        })
        return result.data
    }

    static async getBookingByConfirmationCode(bookingCode) {
        const result = await axios.get(`${this.BASE_URL}/bookings/confirmation/${bookingCode}`)
        return result.data
    }

    static async cancelBooking(bookingId) {
        const result = await axios.delete(`${this.BASE_URL}/bookings/${bookingId}`, {
            headers: this.getHeader()
        })
        return result.data
    }

    
    static logout() {
        localStorage.removeItem('token')
        localStorage.removeItem('role')
    }

    static isAuthenticated() {
        const token = localStorage.getItem('token')
        return !!token
    }

    static isAdmin() {
        const role = localStorage.getItem('role')
        return role === 'ADMIN'
    }

    static isUser() {
        const role = localStorage.getItem('role')
        return role === 'USER'
    }
}