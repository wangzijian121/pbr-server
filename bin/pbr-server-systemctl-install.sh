#!/bin/bash

SERVICE_NAME=pbr-server

SERVICE_DESCRIPTION="Algorithm Management API"
SERVICE_USER=youruser
SCRIPT_DIR=$(dirname "$0")
SYSTEMCTL_DIR=$(dirname "$(readlink -f "$0")")
START_SCRIPT_PATH=$SYSTEMCTL_DIR/start.sh
STOP_SCRIPT_PATH=$SYSTEMCTL_DIR/stop.sh
SYSTEMD_PATH=/etc/systemd/system

# Create systemd service file
echo "[Unit]
Description=$SERVICE_DESCRIPTION
After=network.target

[Service]
User=root
Type=forking
WorkingDirectory=$SYSTEMCTL_DIR
ExecStart=$START_SCRIPT_PATH
ExecStop=$STOP_SCRIPT_PATH
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure

[Install]
WantedBy=multi-user.target" > $SYSTEMD_PATH/$SERVICE_NAME.service

# Reload systemd to pick up the new service
sudo systemctl daemon-reload

# Enable the service to start on boot
sudo systemctl enable $SERVICE_NAME

# Start the service
sudo systemctl start $SERVICE_NAME
