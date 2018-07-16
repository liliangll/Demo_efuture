var projectName = getRootPath();

$(function() {
	// alert("hello");
});

function exportSiteInfoExcel(excelFormat) {
	window.location.href = projectName
			+ "/exportExcel?myid=2&excelFormat=" + excelFormat
			+ "&fileName=xxx";
}

function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName
			.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'
			+ webName;// + '/';
}