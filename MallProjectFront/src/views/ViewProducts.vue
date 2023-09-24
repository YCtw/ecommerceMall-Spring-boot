<template>
    <main>
        <Navbar />

        <!-- table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Products on sell</h1>
                    
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Product ID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="product in products" :key="product.productID">
                                <th scope="row">{{ product.productID }}</th>
                                <td>{{ product.productName }}</td>
                                <td>{{ product.price }}</td>
                                <td>{{ product.quantity }}</td>
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
    name: 'ViewProducts',
    components: {
        Navbar
    },
    data(){
        return {
            products: []
        }
    },

    beforeMount(){
        this.getProducts()
    },

    methods: {
        getProducts(){
            fetch('http://localhost:8080/products')
            .then(res => res.json())
            .then(data => {
                this.products = data
                console.log(data)
            })
        }
    }
}
</script>