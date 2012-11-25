module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: '<json:package.json>',
    jasmine: {
      src: 'lib/**/*.js',
      specs: 'test/**/*.js'
    },
    lint: {
      files: ['grunt.js', 'lib/**/*.js']
    },
    watch: {
      files: '<config:lint.files>',
      tasks: 'default'
    },
    jshint: {
      options: {
        curly: true,
        eqeqeq: true,
        immed: true,
        latedef: true,
        newcap: true,
        noarg: true,
        sub: true,
        undef: true,
        boss: true,
        eqnull: true,
        node: true
      },
      globals: {
        exports: true
      }
    }
  });

  grunt.loadNpmTasks('grunt-jasmine-runner');
  
  // Default task.
  grunt.registerTask('default', 'lint jasmine');
  grunt.registerTask('tdd', 'jasmine-server');
};