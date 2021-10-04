$(document).ready(function () {
    $('#btnShow').click(function (){
        ProductionCycleService.getAll();
    });

    $('btnAdd').click(function (){
        $('#tlbProductionCycle').empty();
        $('#tlbProductionCycle').hide();
        $("#saveForm").show();
    });

    $('#saveForm').submit(function (event) {
        event.preventDefault();

        var id = document.getElementById('productionCycleId').value;
        var status = document.getElementById('status').value;
        var assemblyLineId = document.getElementById('assemblyLineId').value;

        var formData = JSON.stringify({
            'id': id,
            'status': status,
            'assemblyLineId': assemblyLineId
        })

        if(id){
            ProductionCycleService.put(formData);
        } else {
            ProductionCycleService.post(formData);
        }
    });
});