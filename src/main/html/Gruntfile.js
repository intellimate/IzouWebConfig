module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({

    });

    // Load the plugins that provide various tasks
    grunt.loadNpmTasks('grunt-contrib-uglify');

    // Default task(s).
    grunt.registerTask('default', ['uglify']);

};