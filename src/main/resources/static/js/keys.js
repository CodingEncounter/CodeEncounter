const fileStack = 'AX1Jt8rFiRvGNuWPENMkoz';
const client = filestack.init(fileStack);

const options = {
    fromSources: ["local_file_system"],
    accept: ["image/*"],
    onFileUploadFinished: callback => {
        const filestackUrl = callback.url;
        $('#photo').val(filestackUrl);
        alert("Upload Success!")
    }
};
$('#upload').click(function (event){
    event.preventDefault();
    client.picker(options).open();
})

function validatePhoto(){
    $(document.getElementById($('submitAddPhoto'))).css('display: block;');
}