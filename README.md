# BORGI

## Основная конечная точка API

Базовый URL для пользовательского API: `https://borgi.webfun.cf/`.
Все запросы и ответы отправляются по HTTPS.

## Все конечные точки и ожидаемые данные

`GET /get_lang`:

```json
{
  "message": "ok",
  "success": "1",
  "data": {
    "ru": {
      
    },
    "ro": {
      
    },
    "en": {
      
    }
  }
}
```

`POST /login` *email *password:

```json
{
  "message": "ok",
  "success": "1",
  "data": {
    "firstName": "TestName",
    "lastName": "TestSurname",
    "birthDate": "11.05.2022",
    "email": "TestEmail"
  }
}
```
`POST /reports_info` *car_id: 1, *date: "01.01.2023", *fuelAmount: 123.5, *speedometerValue:2569, *
fuelPrice: 1.5

```json
{
  "message": "ok",
  "success": "1",
  "data": {}
}
```