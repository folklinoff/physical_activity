FROM golang as builder
WORKDIR /app
COPY go.mod go.sum ./
RUN go mod download
COPY . ./
RUN GOOS=linux go build -o ./build .

FROM ubuntu
COPY --from=builder /app/build /home/charity/app
ADD ./.aws /root/.aws
EXPOSE 8080 8080
CMD ["./home/charity/app"]
