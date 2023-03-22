
set TABLE_NAME=surf-spots
set DEF_FILE_DIR=C:\Users\cmowr\Desktop\Dev_Projects\inlandnw-surf\api\dynamodb\table-defs
set DATA_FILE_DIR=C:\Users\cmowr\Desktop\Dev_Projects\inlandnw-surf\api\dynamodb\data
set TABLE_DEF=states_def.json
set STATE_DATA_FILE=states.json
set ENDPOINT_URL=http://localhost:8000


aws dynamodb delete-table --table-name %TABLE_NAME% --endpoint-url %ENDPOINT_URL%
aws dynamodb create-table --cli-input-json file://%DEF_FILE_DIR%\%STATE_FILE_DEF% --endpoint-url %ENDPOINT_URL%
aws dynamodb batch-write-item --request-items file://%DATA_FILE_DIR%\%STATE_DATA_FILE% --endpoint-url %ENDPOINT_URL%
aws dynamodb scan --table-name %TABLE_NAME% --endpoint-url %ENDPOINT_URL%