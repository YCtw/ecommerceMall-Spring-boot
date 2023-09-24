<template>
    <main>
        <Navbar/>
        <div class="my-5">
            <div class="mx-auto w-25" style="max-width: 100;">
                <h2 class="text-center mb-3">Purchase Product</h2>
                <form @submit.prevent="submitForm">
      <div class="row" v-for="(product, index) in products" :key="product.id">
        <div class="col-md-12 form-group mb-3">
          <label :for="'product_' + index">商品名稱:  {{ product.productName }}</label>
          <select :id="'product_' + index" v-model="selectedProducts[index].quantity">
            <option v-for="quantity in product.availableQuantities" :key="quantity" :value="quantity">
              {{ quantity }}
            </option>
          </select>
        </div>
      </div>
    
      <div class="row">
        <div class="col-md-12 form-group mb-3">
        <label for="customerID" class="form-label">Customer ID</label>
        <input type="text" v-model="customerID" name="customerID" id="customerID" class="form-control" placeholder="customerID">
    </div>
      </div>

      <div class="row">
        <div class="col-md-12 form-group mb-3">
        <label for="payStatus">Payment Status:</label>
        <select id="payStatus" v-model="payStatus">
          <option value="0">Paid Latter</option>
          <option value="1">Paid Now</option>
        </select>
    </div>
      </div>
      
      
      <div class="mb-3">
        <button class="btn btn-primary" type="submit">Submit</button>
      </div>
    </form>
            </div>
        </div>
    </main>
</template>

<script>

    import Navbar from '../components/Navbar.vue';

    export default {
        name: 'PurchaseProduct',
        components: {
        Navbar
    },
  data() {
    return {
      products: [],
      selectedProducts: [],
      customerID: '',
      payStatus: '0'
    };
  },
  mounted() {
    // Fetch product data from your backend when the component is mounted
    this.fetchProducts();
  },
  methods: {
    fetchProducts() {
      // Make an HTTP request to your backend to fetch the product data
      fetch('http://localhost:8080/products')
        .then(response => response.json())
        .then(data => {
          this.products = data; // Assuming the response is an array of products
          // Initialize the selectedProducts array based on the fetched products
          this.selectedProducts = this.products.map(product => ({
            productName: product.productName,
            quantity: 0,
            price: product.price
          }));
          this.products.forEach(product => {
            product.availableQuantities = Array.from({ length: product.quantity + 1 }, (_, i) => i);
          });
        })
        .catch(error => {
          console.error('Error:', error);
        });
    },
    submitForm() {
      const selectedProducts = this.selectedProducts
        .filter(product => product.quantity > 0)
        .map(product => ({
          productName: product.productName,
          quantity: product.quantity,
          price: product.price
        }));

      if (selectedProducts.length === 0) {
        // Handle case when no products are selected
        alert('Please select at least one product.');
        return;
      }

      const totalPrice = selectedProducts.reduce((total, product) => {
        return total + (product.price * product.quantity);
        }, 0);

      console.log('Total Price:', totalPrice);
      console.log(selectedProducts);

      const purchaseOrder = {
        purchaseProducts: selectedProducts,
        customerID: this.customerID,
        payStatus: this.payStatus,
        totalAmount: totalPrice
        };
        
        console.log('Purchase order data:', purchaseOrder);
        console.log(this.customerID);

      // Send the selected products and purchase amounts to your backend
      fetch('http://localhost:8080/purchaseProducts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(purchaseOrder)
      })
        .then(response => {
          if (response.ok) {
            // Redirect or provide further feedback as needed
            this.$router.push('/orders');
            alert('Thanks for the order, Check your order!');
          } else {
            // Handle errors if needed
            console.error('Error:', response.statusText);
          }
        })
        .catch(error => {
          console.error('Error:', error);
        });
        }
    }
}
</script>