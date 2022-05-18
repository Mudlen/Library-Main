let libraryApi = Vue.resource('/api/library-storage/1.0.0/library/book{/id}');
let storageApi = Vue.resource('/api/library-storage/1.0.0/storage/book{/id}');
Vue.component('base-head-table', {
   template:
       '<thead class="table-primary">' +
            '<tr>' +
                '<th class="text-start" style="width: 55%">Name</th>'+
                '<th class="text-center" style="width: 10%">Format</th>'+
                '<th class="text-center" style="width: 10%">Download</th>'+
                '<th class="text-center" style="width: 5%"></th>'+
            '</tr>'+
       '</thead>'
});

Vue.component('base-body-table', {
    props: ['files'],
    template:
        '<tbody class="search-input">' +
            '<tr class="" v-for="file in files" v-bind:key="file.id">' +
                '<td class=""> <a v-bind:href="`pdf-view/` + file.id"> {{ file.simpleName }} </a> </td>' +
                '<td class="text-center">{{ file.format }}</td>' +
                '<td class="text-center"> <a @click="download(file.id,file.simpleName)"> <span class="bi-download"></span> </a></td>' +
                '<td class="text-center"> <button style="color: darkorange" class="btn bi-star-fill"></button> </td>' +
            '</tr>' +
        '</tbody>',
    methods: {
        download: function (id,simpleName){
            storageApi.get({id: id})
                .then(result =>
                    result.blob().then(blob => {
                        let link = document.createElement('a');
                        let binary = [];
                        binary.push(blob);
                        link.href = window.URL
                            .createObjectURL(new Blob(binary,
                                {type: "application/pdf"}));
                        link.setAttribute("download",simpleName)
                        link.click();
                    }))
        }
    }

});

Vue.component('base-container', {
    props: ['files'],
    template:
        '<div class="container" style="max-width: 1000px; margin-top: 70px">' +
            '<div class="row ">'+
                '<div class="col-sm-3"><h2>Public books:</h2></div>' +
                '<div style="margin-left: -3em" class="col-sm-3"><input  id="search-input" class="form-control h-75" type="search" placeholder="Search"></div>' +
            '</div>' +
            '<table class="table table-dark table-hover table-striped table-bordered">' +
                '<base-head-table></base-head-table>' +
                '<base-body-table v-bind:files="files"></base-body-table>' +
            '</table>'+
        '</div>',
    created: function () {
        libraryApi.get().then(result =>
            result.json().then(data =>
                data.forEach(file => this.files.push(file))))
    }
});
//🔍
let publicPDF = new Vue({
    el: '#publicPDF',
    template: '<base-container v-bind:files="files"></base-container>',
    data: {
        files: []
    }
});