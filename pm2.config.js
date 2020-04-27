module.exports = {
    apps: [
        {
            name: 'tcp-client-master',
            script: 'java',
            args: [
                '-jar',
                'target/tcp-client-master-0.0.1.jar'
            ],
            cwd: '.',
            interpreter: ''
        }
    ]
}
