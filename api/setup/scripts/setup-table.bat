
set TABLE_NAME=surf-spots
set DEF_FILE_DIR=C:\Users\cmowr\Desktop\Dev_Projects\inlandnw-surf\api\setup\dynamodb\table-defs
set DATA_FILE_DIR=C:\Users\cmowr\Desktop\Dev_Projects\inlandnw-surf\api\setup\dynamodb\data
set TABLE_DEF=surf-spots-def.json
set DATA_FILE=surf-spots.json
set ENDPOINT_URL=http://localhost:8000


aws dynamodb delete-table --table-name %TABLE_NAME% --endpoint-url %ENDPOINT_URL%
aws dynamodb create-table --cli-input-json file://%DEF_FILE_DIR%\%TABLE_DEF% --endpoint-url %ENDPOINT_URL%
aws dynamodb batch-write-item --request-items file://%DATA_FILE_DIR%\%DATA_FILE% --endpoint-url %ENDPOINT_URL%
REM aws dynamodb scan --table-name %TABLE_NAME% --endpoint-url %ENDPOINT_URL%