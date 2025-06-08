package com.delhomme.jobbingtrack.data.local.dao.user;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.delhomme.jobbingtrack.data.local.entities.user.UserEntity;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<UserEntity> __insertAdapterOfUserEntity;

  private final EntityDeleteOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfUserEntity = new EntityInsertAdapter<UserEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`email`,`password`,`firstName`,`lastName`,`job`,`phoneNumber`,`address`,`city`,`postalCode`,`country`,`latitude`,`longitude`,`lastLogin`,`profilePicture`,`subject`,`notes`,`syncHash`,`isArchived`,`isDeleted`,`createdAt`,`updatedAt`,`deletedAt`,`archivedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getEmail());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getPassword());
        }
        if (entity.getFirstName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getFirstName());
        }
        if (entity.getLastName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getLastName());
        }
        if (entity.getJob() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getJob());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getPhoneNumber());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getCity());
        }
        if (entity.getPostalCode() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getPostalCode());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getCountry());
        }
        statement.bindDouble(12, entity.getLatitude());
        statement.bindDouble(13, entity.getLongitude());
        if (entity.getLastLogin() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getLastLogin());
        }
        if (entity.getProfilePicture() == null) {
          statement.bindNull(15);
        } else {
          statement.bindText(15, entity.getProfilePicture());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getSubject());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(18);
        } else {
          statement.bindText(18, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(19, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(20, _tmp_1);
        statement.bindLong(21, entity.getCreatedAt());
        statement.bindLong(22, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(23);
        } else {
          statement.bindLong(23, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(24);
        } else {
          statement.bindLong(24, entity.getArchivedAt());
        }
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeleteOrUpdateAdapter<UserEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`email` = ?,`password` = ?,`firstName` = ?,`lastName` = ?,`job` = ?,`phoneNumber` = ?,`address` = ?,`city` = ?,`postalCode` = ?,`country` = ?,`latitude` = ?,`longitude` = ?,`lastLogin` = ?,`profilePicture` = ?,`subject` = ?,`notes` = ?,`syncHash` = ?,`isArchived` = ?,`isDeleted` = ?,`createdAt` = ?,`updatedAt` = ?,`deletedAt` = ?,`archivedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getEmail());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getPassword());
        }
        if (entity.getFirstName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getFirstName());
        }
        if (entity.getLastName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getLastName());
        }
        if (entity.getJob() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getJob());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getPhoneNumber());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getCity());
        }
        if (entity.getPostalCode() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getPostalCode());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, entity.getCountry());
        }
        statement.bindDouble(12, entity.getLatitude());
        statement.bindDouble(13, entity.getLongitude());
        if (entity.getLastLogin() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getLastLogin());
        }
        if (entity.getProfilePicture() == null) {
          statement.bindNull(15);
        } else {
          statement.bindText(15, entity.getProfilePicture());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(16);
        } else {
          statement.bindText(16, entity.getSubject());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(17);
        } else {
          statement.bindText(17, entity.getNotes());
        }
        if (entity.getSyncHash() == null) {
          statement.bindNull(18);
        } else {
          statement.bindText(18, entity.getSyncHash());
        }
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(19, _tmp);
        final int _tmp_1 = entity.isDeleted() ? 1 : 0;
        statement.bindLong(20, _tmp_1);
        statement.bindLong(21, entity.getCreatedAt());
        statement.bindLong(22, entity.getUpdatedAt());
        if (entity.getDeletedAt() == null) {
          statement.bindNull(23);
        } else {
          statement.bindLong(23, entity.getDeletedAt());
        }
        if (entity.getArchivedAt() == null) {
          statement.bindNull(24);
        } else {
          statement.bindLong(24, entity.getArchivedAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(25);
        } else {
          statement.bindText(25, entity.getId());
        }
      }
    };
  }

  @Override
  public Object upsert(final UserEntity user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfUserEntity.insert(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final UserEntity user, final Continuation<? super Unit> $completion) {
    if (user == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfUserEntity.handle(_connection, user);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<UserEntity>> getAll() {
    final String _sql = "SELECT * FROM users ORDER BY email";
    return FlowUtil.createFlow(__db, false, new String[] {"users"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPassword = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "password");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfJob = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "job");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
        final int _columnIndexOfCity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "city");
        final int _columnIndexOfPostalCode = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postalCode");
        final int _columnIndexOfCountry = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "country");
        final int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
        final int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
        final int _columnIndexOfLastLogin = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLogin");
        final int _columnIndexOfProfilePicture = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicture");
        final int _columnIndexOfSubject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subject");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final List<UserEntity> _result = new ArrayList<UserEntity>();
        while (_stmt.step()) {
          final UserEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPassword;
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null;
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpJob;
          if (_stmt.isNull(_columnIndexOfJob)) {
            _tmpJob = null;
          } else {
            _tmpJob = _stmt.getText(_columnIndexOfJob);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final String _tmpAddress;
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null;
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress);
          }
          final String _tmpCity;
          if (_stmt.isNull(_columnIndexOfCity)) {
            _tmpCity = null;
          } else {
            _tmpCity = _stmt.getText(_columnIndexOfCity);
          }
          final String _tmpPostalCode;
          if (_stmt.isNull(_columnIndexOfPostalCode)) {
            _tmpPostalCode = null;
          } else {
            _tmpPostalCode = _stmt.getText(_columnIndexOfPostalCode);
          }
          final String _tmpCountry;
          if (_stmt.isNull(_columnIndexOfCountry)) {
            _tmpCountry = null;
          } else {
            _tmpCountry = _stmt.getText(_columnIndexOfCountry);
          }
          final double _tmpLatitude;
          _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
          final double _tmpLongitude;
          _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
          final Long _tmpLastLogin;
          if (_stmt.isNull(_columnIndexOfLastLogin)) {
            _tmpLastLogin = null;
          } else {
            _tmpLastLogin = _stmt.getLong(_columnIndexOfLastLogin);
          }
          final String _tmpProfilePicture;
          if (_stmt.isNull(_columnIndexOfProfilePicture)) {
            _tmpProfilePicture = null;
          } else {
            _tmpProfilePicture = _stmt.getText(_columnIndexOfProfilePicture);
          }
          final String _tmpSubject;
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null;
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _item = new UserEntity(_tmpId,_tmpEmail,_tmpPassword,_tmpFirstName,_tmpLastName,_tmpJob,_tmpPhoneNumber,_tmpAddress,_tmpCity,_tmpPostalCode,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpLastLogin,_tmpProfilePicture,_tmpSubject,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<UserEntity> getById(final String id) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"users"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfEmail = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "email");
        final int _columnIndexOfPassword = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "password");
        final int _columnIndexOfFirstName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "firstName");
        final int _columnIndexOfLastName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastName");
        final int _columnIndexOfJob = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "job");
        final int _columnIndexOfPhoneNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "phoneNumber");
        final int _columnIndexOfAddress = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "address");
        final int _columnIndexOfCity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "city");
        final int _columnIndexOfPostalCode = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "postalCode");
        final int _columnIndexOfCountry = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "country");
        final int _columnIndexOfLatitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "latitude");
        final int _columnIndexOfLongitude = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "longitude");
        final int _columnIndexOfLastLogin = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastLogin");
        final int _columnIndexOfProfilePicture = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "profilePicture");
        final int _columnIndexOfSubject = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subject");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfSyncHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "syncHash");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfDeletedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deletedAt");
        final int _columnIndexOfArchivedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "archivedAt");
        final UserEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpEmail;
          if (_stmt.isNull(_columnIndexOfEmail)) {
            _tmpEmail = null;
          } else {
            _tmpEmail = _stmt.getText(_columnIndexOfEmail);
          }
          final String _tmpPassword;
          if (_stmt.isNull(_columnIndexOfPassword)) {
            _tmpPassword = null;
          } else {
            _tmpPassword = _stmt.getText(_columnIndexOfPassword);
          }
          final String _tmpFirstName;
          if (_stmt.isNull(_columnIndexOfFirstName)) {
            _tmpFirstName = null;
          } else {
            _tmpFirstName = _stmt.getText(_columnIndexOfFirstName);
          }
          final String _tmpLastName;
          if (_stmt.isNull(_columnIndexOfLastName)) {
            _tmpLastName = null;
          } else {
            _tmpLastName = _stmt.getText(_columnIndexOfLastName);
          }
          final String _tmpJob;
          if (_stmt.isNull(_columnIndexOfJob)) {
            _tmpJob = null;
          } else {
            _tmpJob = _stmt.getText(_columnIndexOfJob);
          }
          final String _tmpPhoneNumber;
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null;
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber);
          }
          final String _tmpAddress;
          if (_stmt.isNull(_columnIndexOfAddress)) {
            _tmpAddress = null;
          } else {
            _tmpAddress = _stmt.getText(_columnIndexOfAddress);
          }
          final String _tmpCity;
          if (_stmt.isNull(_columnIndexOfCity)) {
            _tmpCity = null;
          } else {
            _tmpCity = _stmt.getText(_columnIndexOfCity);
          }
          final String _tmpPostalCode;
          if (_stmt.isNull(_columnIndexOfPostalCode)) {
            _tmpPostalCode = null;
          } else {
            _tmpPostalCode = _stmt.getText(_columnIndexOfPostalCode);
          }
          final String _tmpCountry;
          if (_stmt.isNull(_columnIndexOfCountry)) {
            _tmpCountry = null;
          } else {
            _tmpCountry = _stmt.getText(_columnIndexOfCountry);
          }
          final double _tmpLatitude;
          _tmpLatitude = _stmt.getDouble(_columnIndexOfLatitude);
          final double _tmpLongitude;
          _tmpLongitude = _stmt.getDouble(_columnIndexOfLongitude);
          final Long _tmpLastLogin;
          if (_stmt.isNull(_columnIndexOfLastLogin)) {
            _tmpLastLogin = null;
          } else {
            _tmpLastLogin = _stmt.getLong(_columnIndexOfLastLogin);
          }
          final String _tmpProfilePicture;
          if (_stmt.isNull(_columnIndexOfProfilePicture)) {
            _tmpProfilePicture = null;
          } else {
            _tmpProfilePicture = _stmt.getText(_columnIndexOfProfilePicture);
          }
          final String _tmpSubject;
          if (_stmt.isNull(_columnIndexOfSubject)) {
            _tmpSubject = null;
          } else {
            _tmpSubject = _stmt.getText(_columnIndexOfSubject);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpSyncHash;
          if (_stmt.isNull(_columnIndexOfSyncHash)) {
            _tmpSyncHash = null;
          } else {
            _tmpSyncHash = _stmt.getText(_columnIndexOfSyncHash);
          }
          final boolean _tmpIsArchived;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp != 0;
          final boolean _tmpIsDeleted;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsDeleted));
          _tmpIsDeleted = _tmp_1 != 0;
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final Long _tmpDeletedAt;
          if (_stmt.isNull(_columnIndexOfDeletedAt)) {
            _tmpDeletedAt = null;
          } else {
            _tmpDeletedAt = _stmt.getLong(_columnIndexOfDeletedAt);
          }
          final Long _tmpArchivedAt;
          if (_stmt.isNull(_columnIndexOfArchivedAt)) {
            _tmpArchivedAt = null;
          } else {
            _tmpArchivedAt = _stmt.getLong(_columnIndexOfArchivedAt);
          }
          _result = new UserEntity(_tmpId,_tmpEmail,_tmpPassword,_tmpFirstName,_tmpLastName,_tmpJob,_tmpPhoneNumber,_tmpAddress,_tmpCity,_tmpPostalCode,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpLastLogin,_tmpProfilePicture,_tmpSubject,_tmpNotes,_tmpSyncHash,_tmpIsArchived,_tmpIsDeleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeletedAt,_tmpArchivedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object archive(final String id, final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE users SET isArchived = 1 WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM users WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM users";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
