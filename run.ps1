Write-Host "docker..." -ForegroundColor Cyan
docker-compose down -v
docker-compose up -d --build

Write-Host "--- Waiting 10 seconds for MySQL ... ---" -ForegroundColor Yellow
Start-Sleep -Seconds 10

docker-compose restart bank-api

Write-Host "success" -ForegroundColor Green
Write-Host "frontend: http://localhost:5173" -ForegroundColor Yellow
