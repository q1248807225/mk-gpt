const { createApp } = Vue;

createApp({
    data() {
        return {
            name: '',
            hunger: 0,
            happiness: 0,
            apiBase: '/api'
        };
    },
    methods: {
        loadState() {
            axios.get(this.apiBase + '/pet').then(res => {
                Object.assign(this, res.data);
            });
        },
        feed() {
            axios.post(this.apiBase + '/feed').then(res => {
                Object.assign(this, res.data);
            });
        },
        play() {
            axios.post(this.apiBase + '/play').then(res => {
                Object.assign(this, res.data);
            });
        },
        renamePet() {
            const newName = prompt('给你的宠物起个名字:', this.name);
            if (newName) {
                axios.post(this.apiBase + '/rename', { name: newName }).then(res => {
                    Object.assign(this, res.data);
                });
            }
        }
    },
    mounted() {
        this.loadState();
    }
}).mount('#app');
