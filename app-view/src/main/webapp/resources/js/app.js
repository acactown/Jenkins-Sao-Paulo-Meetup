/* *****************************
 * ****** Notifications ********
 * *****************************/
var Notifications = {
  $container: $('#notification'),
  updateNotification: function (level, title, msg) {
    this.$container.html('<div class="alert fade in ' + level + '"><button type="button" class="close" data-dismiss="alert">×</button><strong>' + title + '</strong> ' + msg + '</div>');
  },
  success: function (title, msg) {
    this.updateNotification('alert-success', title, msg);
  },
  warn: function (title, msg) {
    this.updateNotification('alert-warn', title, msg);
  },
  info: function (title, msg) {
    this.updateNotification('alert-info', title, msg);
  },
  error: function (title, msg) {
    this.updateNotification('alert-error', title, msg);
  }
};

Modal = function () {
  this.options = null;
  this.$modal = $('#modal');
  this.$submit = this.$modal.find('#modalSubmit');
  this.$title = this.$modal.find('#modalTitle');
};

Modal.prototype.setOptions = function (options) {
  this.options = options;
};

Modal.prototype.show = function () {
  var self = this,
    btnClass = 'btn-primary';
  switch (this.options.action) {
    case 'Create':
      btnClass = 'btn-success';
      break;
    case 'Edit':
      btnClass = 'btn-warning';
      break;
    case 'Delete':
      btnClass = 'btn-danger';
      break;
  }
  this.$title.html(this.options.title);
  this.$submit.html(this.options.action);
  this.$submit.removeClass().addClass('btn ' + btnClass);
  this.$submit.on('click', function (event) {
    event.preventDefault();
    var $form = $('#modal-form');
    $.ajax({
      type: self.options.method,
      url: $form.attr('action'),
      data: $form.serialize(),
      contentType: 'application/x-www-form-urlencoded',
      cache: false
    });
    self.hide();
  });
  this.$modal.removeData('modal');
  this.$modal.modal({
    keyboard: true,
    show: true,
    remote: self.options.url
  });
};

Modal.prototype.hide = function () {
  this.$modal.modal('hide');
};

/* ******************************
 * ****** Object Factory ********
 * ******************************/
Factory = function (el, selector) {
  var self = this;
  if (el && el.length > 0) {
    this.$container = el;
    this.selector = selector;
    this.$createBtn = this.$container.find('.factory-create');
    this.$editBtn = this.$container.find('.factory-edit');
    this.$deleteBtn = this.$container.find('.factory-delete');

    this.$createBtn.on('click', function (event) {
      event.preventDefault();
      var options = {
        action: 'Create',
        title: 'Create new ' + self.selector,
        url: this.href,
        method: 'POST'
      };
      modal.setOptions(options);
      modal.show();
    });

    this.$editBtn.on('click', function (event) {
      event.preventDefault();
      var options = {
        action: 'Edit',
        title: 'Edit ' + self.selector,
        url: this.href,
        method: 'PUT'
      };
      modal.setOptions(options);
      modal.show();
    });

    this.$deleteBtn.on('click', function (event) {
      event.preventDefault();
      var options = {
        action: 'Delete',
        title: 'Delete ' + self.selector,
        url: this.href,
        method: 'DELETE'
      };
      modal.setOptions(options);
      modal.show();
    });
  }
};

var modal = new Modal();

$(function () {
  var templateFactory = new Factory($('#jobs'), 'Job');
  setInterval(function () {
    loadJobs()
  }, 5000);
});

function loadJobs() {
  $.get("/jobs/api", function (data) {
    var jobs = data.jobs;
    var conten = "", button, status, name, url;
    $.each(jobs, function (index, job) {
      status = job.color;
      name = job.name;
      url = job.url;
      button = getButton(status);
      conten += '<tr class="'+button.rowsClass+'"><td><a href="' + url + '" role="button" class="btn ' + button.btnClass + '" target="_blank"><i class="icon-trash ' + button.btnIcon + '"></i></a></td>';
      conten += '<td>' + status + '</td>';
      conten += '<td>' + name + '</td></tr>';
    });
    $('#jobs').html(conten);
  });
}

function getButton(status) {
  var button = {btnClass: 'btn-inverse', btnIcon: 'icon-exclamation-sign', rowsClass: 'abched'};
  switch (status) {
    case "blue":
      button = {btnClass: 'btn-info', btnIcon: 'icon-thumbs-up', rowsClass: 'info'};
      break;
    case "red":
      button = {btnClass: 'btn-danger', btnIcon: 'icon-thumbs-down', rowsClass: 'error'};
      break;
    case "yellow":
      button = {btnClass: 'btn-warning', btnIcon: 'icon-warning-sign', rowsClass:'warning'};
      break;
  }

  return button;
}
