terraform {
  required_providers {
    random = {
      source  = "hashicorp/random"
      version = "3.6.2"
    }
  }
}

resource "random_string" "random" {
  length  = 16
  special = true
  upper   = true
  lower   = true
  numeric = true
}

output "random_string" {
  value = random_string.random.result
}