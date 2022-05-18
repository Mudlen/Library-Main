document.querySelector('#search-input').oninput = function () {
    let val = this.value.trim();
    val = val.toLowerCase();
    let items = document.querySelectorAll('.search-input tr');
    if (val !== ''){
        items.forEach(function (elem){
           if(elem.innerText.toLowerCase().search(val) === -1){
               elem.classList.add('search-visible-false');
           }else {
               elem.classList.remove('search-visible-false')
           }
        });
    }else {
        items.forEach(function (elem){
            elem.classList.remove('search-visible-false')
        });
    }
}