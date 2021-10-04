const path = '/production-cycles/v1';
class ProductionCycleService {
    constructor(){}

    static getAll() {
        BaseService.getAll(path)
            .then(function (productionCycleDtoList) {
                var content = '<table class="production-fill">';
                content += '<tr>'
                    + '<th scope="col">Id</th>'
                    + '<th scope="col">Status</th>'
                    + '<th scope="col">Assembly Line Id</th>'
                    + '<th scope="col">Detail</th>'
                    + '<th scope="col">Action</th>'
                    + '</tr>';
                jQuery.each(productionCycleDtoList, function (i, val) {
                    content += '<tr>'
                        + '<td>' + val.id + '</td>'
                        + '<td>' + val.status + '</td>'
                        + '<td>' + val.assemblyLineId + '</td>'
                        + '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="ProductionCycleService.detail(' + val.id + ')">detail</button></td>'
                        + '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="ProductionCycleService.delete(' + val.id + ')">delete</button></td>'
                        + '</tr>';
                });
                content += '</table>';

                $('#saveForm').trigger('reset');
                $('#saveForm').hide();
                $('#tblProductionCycle').empty();
                $('#tblProductionCycle').append(content);
                $('#tblProductionCycle').show();
            });
    }

    static post(formData) {
        BaseService.post(path, formData)
            .then(function (productionCycleDto) {
                window.alert("Good saving? " + productionCycleDto.id != null)
                $('#saveForm').trigger('reset');
                $('#saveForm').hide();

                ProductionCycleService.getAll();
            });
    }

    static put(formData) {
        BaseService.put(path, formData)
            .then(function (productionCycleDto) {
                window.alert("Production cycle updated")
                $('#saveForm').trigger('reset');
                $('#saveForm').hide();

                ProductionCycleService.getAll();
            });
    }

    static delete(id) {
        BaseService.delete(path, id)
            .then(function () {
                window.alert("Production cycle n." + id + "deleted successfully")

                ProductionCycleService.getAll();
            });
    }

    static detail(id) {
        BaseService.get(path, id)
            .then(function (productionCycleDto) {
                document.getElementById('productionCycleId').value = productionCycleDto.id;
                document.getElementById('status').value = productionCycleDto.status;
                document.getElementById('assemblyLineId').value = productionCycleDto.assemblyLineId;

                $('#tblProductionCycle').empty();
                $('#tblProductionCycle').hide();
                $("#saveForm").show();
            });
    }
}