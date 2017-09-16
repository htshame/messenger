$( document ).ready(function() {
    this.messageId = null;
    this.gridBuild = function() {
        $.ajax({
            type: 'GET',
            url: '/messenger/messages/getMyMessages',
            dataType: 'json',
            success: function(data) {
                var grid;
                var loader = new Slick.Data.RemoteModel();
                loader.data = data;
                var dateFormatter = function (row, cell, value, columnDef, dataContext) {
                    var newDate = new Date();
                    newDate.setTime(value);
                    dateString = newDate.toUTCString();
                    return dateString;
                };
                var columns = [
                    {id: "id", name: "Id", field: "id", width: 60, sortable: true},
                    {id: "sender", name: "Sender",  field: "sender", width: 160, sortable: true},
                    {id: "theme", name: "Theme", field: "theme", width: 160, sortable: true},
                    {id: "text", name: "Text", field: "text", width: 320, sortable: true},
                    {id: "date", name: "Date", field: "date", width: 320, sortable: true, formatter: dateFormatter}
                    ];
                currentSortCol = { id: "title" };
                function getLength() {
                    return data.length;
                }
                var options = {
                        rowHeight: 64,
                        enableCellNavigation: true,
                        enableColumnReorder: false,
                        multiColumnSort: true
                };
                var loadingIndicator = null;
                $(function () {
                    grid = new Slick.Grid("#myGrid", loader.data, columns, options);
                    grid.onViewportChanged.subscribe(function (e, args) {
                        var vp = grid.getViewport();
                        loader.ensureData(vp.top, vp.bottom);
                    });
                    grid.onSort.subscribe(function (e, args) {
                        var cols = args.sortCols;
                        data.sort(function (dataRow1, dataRow2) {
                            for (var i = 0, l = cols.length; i < l; i++) {
                                var field = cols[i].sortCol.field;
                                var sign = cols[i].sortAsc ? 1 : -1;
                                var value1 = dataRow1[field], value2 = dataRow2[field];
                                var result = (value1 == value2 ? 0 : (value1 > value2 ? 1 : -1)) * sign;
                                if (result != 0) {
                                    return result;
                                }
                            }
                            return 0;
                        });
                        grid.invalidate();
                        grid.render();
                    });
                    grid.onClick.subscribe(function(e, args) {
                        var item = args.grid.getData()[args.row];
                        that.messageId = item.id;
                        $('#mess-sender').val(item.sender);
                        $('#mess-date').val(item.date);
                        $('#mess-theme').val(item.theme);
                        $('#mess-text').val(item.text);
                        $('#dialog-message-read').dialog('open');
                    });
                });
            }
        }).always(function() {
            $('#dialog-message-read').dialog({autoopen: false});
            $('#dialog-message-read').dialog('close');
        });
    }
    var that = this;
    (function() {
        that.gridBuild();
    })();
});
