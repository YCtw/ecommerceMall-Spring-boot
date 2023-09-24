<template>
    <main>
        <Navbar/>
        <div class="my-5">
            <div class="mx-auto w-25" style="max-width: 100%;">
                <h2 class="text-center mb-3">Add Product</h2>
                <form @submit.prevent="addProduct">
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="productID" class="form-label">Product ID</label>
                            <input type="text" name="productID" id="productID" class="form-control" placeholder="Product ID" required v-model="product.productID">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="productName" class="form-label">Product Name</label>
                            <input type="text" name="productName" id="productName" class="form-control" placeholder="Product Name" required v-model="product.productName">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="price" class="form-label">Product Price</label>
                            <input type="text" name="price" id="price" class="form-control" placeholder="Price" required v-model="product.price">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="quantity" class="form-label">Product Quantity</label>
                            <input type="text" name="quantity" id="quantity" class="form-control" placeholder="Quantity" required v-model="product.quantity">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input class="btn btn-primary w-100" type="submit" value="Submit">
                        </div>
                    </div>

                </form>
            </div>
        </div>
  
    </main>
</template>


<script>
import Navbar from '../components/Navbar.vue';

export default{
    name: 'AddProduct',
    components: {
        Navbar
    },

    data(){
        return {
            product: {
                productID: '',
                productName: '',
                price: '',
                quantity: ''
            }
        }
    },

    methods: {
        addProduct(){

            this.product.price = parseInt(this.product.price);
            this.product.quantity = parseInt(this.product.quantity);

            fetch('http://localhost:8080/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.product)
            })
            .then(data => {
                console.log(data)
                this.$router.push("/")
            })
        }
    }
}
</script>