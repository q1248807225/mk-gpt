const { createApp } = Vue;

createApp({
    data() {
        return {
            processKey: 'marketingProcess',
            instanceId: '',
            apiBase: 'http://localhost:8080/api/marketing'
        };
    },
    methods: {
        start() {
            axios.post(`${this.apiBase}/start/${this.processKey}`).then(res => {
                this.instanceId = res.data.id;
            });
        }
    }
}).mount('#app');
