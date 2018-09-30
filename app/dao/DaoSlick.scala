package dao

import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

trait DAOSlick extends DBTableDefinitions with HasDatabaseConfigProvider[JdbcProfile]
