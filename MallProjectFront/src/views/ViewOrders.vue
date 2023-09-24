<template>
    <main>
        <Navbar />

        <!-- table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">All Orders</h1>
                    
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Order ID</th>
                                <th scope="col">Member ID</th>
                                <th scope="col">Price</th>
                                <th scope="col">Pay Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="order in orders" :key="order.orderID">
                                <th scope="row">{{ order.orderID }}</th>
                                <td>{{ order.memberID }}</td>
                                <td>{{ order.price }}</td>
                                <td>{{ order.payStatus }}</td>
                            </tr>
                        </tbody>

                    </table>
                </div>

            </div>

        </div>

    </main>
</template>

<script>
import Navbar from '../components/Navbar.vue';
export default{
    name: 'ViewOrders',
    components: {
        Navbar
    },
    data(){
        return {
            orders: []
        }
    },

    beforeMount(){
        this.getOrders()
    },

    methods: {
        getOrders(){
            fetch('http://localhost:8080/orders')
            .then(res => res.json())
            .then(data => {
                this.orders = data
                console.log(data)
            })
        }
    }
}
</script>